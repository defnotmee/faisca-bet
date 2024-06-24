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
 * Da um erro pois não vamos deixar o cara sacar (a não ser que integremos com o sigma bank)
 */
@WebServlet("/withdraw")
public class ServletWithdraw extends FaiscaServlet {
	private static final long serialVersionUID = 1L;  
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().print("Ops! Tente mais tarde :)");
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
	}

}
