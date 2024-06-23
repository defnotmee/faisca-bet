package br.com.FaiscaAPI;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.com.Faisca.Contas.*;
/**
 * Recebe POST request com os seguintes parâmetros:
 * nome: nome do usuário
 * senha: senha do usuário
 * cpf (opicional): cpf do usuário (só será necessário cpf para sacar :)
 */
@WebServlet("/register")
public class ServletRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String cpf = request.getParameter("cpf");
		if(nome == null) {
			System.err.println("Usuário não colocou nome para registrar.");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		if(senha == null) {
			System.err.println("Usuário não colocou senha para registrar.");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		if(cpf != null) {
			if(!ValidaCPF.isCPF(cpf)) {
				System.err.println("Usuário colocou cpf inválido.");
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
		}
		
		response.setCharacterEncoding("UTF-8");
		
		Conta novaConta = new User(nome,senha,cpf);
		
		System.out.printf("Conta criada com sucesso! Informação da conta: %s\n", ((User) novaConta).toString());
		
		response.getWriter().print(novaConta.getId());
	}

}
