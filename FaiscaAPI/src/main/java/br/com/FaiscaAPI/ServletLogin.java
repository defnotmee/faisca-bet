package br.com.FaiscaAPI;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.Faisca.Contas.Conta;
import br.com.Faisca.Contas.ContasDAO;
import br.com.Faisca.Contas.Hasher;
import br.com.Faisca.Contas.User;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/login")
public class ServletLogin extends FaiscaServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		
		if(email == null) {
			System.err.println("Usuário não colocou email para registrar.");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print("Sem email.");
			return;
		}
		
		if(!Validator.validateEmail(email)) {
			System.err.println("Usuário colocou email inválido.");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print("Email inválido");
			return;
		}
		
		if(senha == null) {
			System.err.println("Usuário não colocou senha para registrar.");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print("Sem senha.");
			return;
		}
		
		Conta conta = ContasDAO.getInstance(getDataPath()).accessEmail(email);
		
		if(conta == null){
			System.err.println("A conta com o email requerido pelo usuário não foi encontrada");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print("Sem conta com esse email.");
			return;
		}
		
		if(Hasher.hash(senha).equals(conta.getHashSenha())){
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
				
		response.getWriter().print(conta.getId());
		
	}
}
