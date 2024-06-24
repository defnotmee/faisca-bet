package br.com.FaiscaAPI;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.Faisca.Jogo.Gerador;
import br.com.Faisca.Jogo.Jogo;
import br.com.Faisca.Jogo.Roleta;
/**
 * Servlet implementation class ServletTeste
 */
@WebServlet("/teste")
public class ServletTeste extends FaiscaServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTeste() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String nome = request.getParameter("nome");
		BigDecimal bet = new BigDecimal(request.getParameter("bet"));
		int choice = Integer.parseInt(request.getParameter("choice"));

		
		System.out.println("Funcionou! O jogador " + nome + " entrou em contato");
		
		PrintWriter pr = response.getWriter();
		Jogo jogo = new Roleta();
		
		pr.println(jogo.play(bet, choice));
	}

}
