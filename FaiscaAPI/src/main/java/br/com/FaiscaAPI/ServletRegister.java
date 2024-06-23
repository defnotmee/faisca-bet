package br.com.FaiscaAPI;

import java.io.IOException;
import java.util.regex.Pattern;

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

	private boolean validateEmail(String email) {
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

		return Pattern.compile(regexPattern).matcher(email).matches();
	}

	private boolean validateCpf(String cpf) {
		return ValidaCPF.isCPF(cpf);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String cpf = request.getParameter("cpf");
		if(nome == null) {
			System.err.println("Usuário não colocou nome para registrar.");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print("Sem nome.");
			return;
		}
		
		if(email == null) {
			System.err.println("Usuário não colocou email para registrar.");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print("Sem email.");
			return;
		}
		
		if(!validateEmail(email)) {
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
		
		if(cpf != null) {
			if(!validateCpf(cpf)) {
				System.err.println("Usuário colocou cpf inválido.");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().print("CPF inválido.");
				return;
			}
		}
		
		Conta novaConta = new User(nome,email,senha,cpf);
		
		System.out.printf("Conta criada com sucesso! Informação da conta: %s\n", ((User) novaConta).toString());
		
		response.getWriter().print(novaConta.getId());
	}

}
