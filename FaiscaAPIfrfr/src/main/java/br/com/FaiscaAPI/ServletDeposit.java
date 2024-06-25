package br.com.FaiscaAPI;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.Faisca.Contas.Conta;
import br.com.Faisca.Contas.ContasDAO;
import br.com.Faisca.Contas.User;

/**
 * Por enquanto você só passa o id da conta e uma senha "secreta"
 * Vamos ver se da pra integrar com o sigma bank depois
 * 
 * A senha NÃO É a senha da conta, e sim uma senha interna
 */
@WebServlet("/deposit")
public class ServletDeposit extends FaiscaServlet {
	private static final long serialVersionUID = 1L;  
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String senha = request.getParameter("senha");
		String sid = request.getParameter("id");
		String sammount = request.getParameter("valor");
		
		
		
		if(senha == null || sid == null || sammount == null) {
			System.err.println("Usuário tentou depositar sem a informação necessária");
			
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print("Sem todos os parametros necessários.");
			return;
		}
		
		Long id;
		BigDecimal ammount;
		
		try {
			id = Long.parseLong(sid);
			ammount = new BigDecimal(sammount);
		} catch(Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print("Mal formulado.");
			return;
		}
		
		
		if(!senha.equals("EuAmoOSigmaBank")) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
			
		}
		
		Conta cur = ContasDAO.getInstance(getContaPath()).accessId(id);
		
		if(cur == null) {
			System.err.println("Usuário tentou depositar em conta inexistente");
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		boolean ret = cur.deposit(ammount);
		
		System.out.println("Depositado na conta de " + cur.getNome() + ", novas informações: " + ((User) cur).toString());
		
		if(ret) {
			response.getWriter().print("Depósito realizado com bônus.");
		} else {
			response.getWriter().print("Depósito realizado sem bônus.");
		}
		
		ContasDAO.getInstance(getContaPath()).persist();
	}

}
