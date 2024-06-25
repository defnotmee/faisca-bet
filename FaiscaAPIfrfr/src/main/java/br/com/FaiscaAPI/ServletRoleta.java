package br.com.FaiscaAPI;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.Faisca.Contas.Conta;
import br.com.Faisca.Contas.ContasDAO;
import br.com.Faisca.Contas.User;
import br.com.Faisca.Jogo.Jogo;
import br.com.Faisca.Jogo.Roleta;
/**
 * Servlet implementation class ServletTeste
 */
@WebServlet("/jogos/roleta")
public class ServletRoleta extends FaiscaServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRoleta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");

		String sid = request.getParameter("id");
		String sbet = request.getParameter("valor");
		String cor = request.getParameter("cor");
		
		if(sid == null || sbet == null || cor == null) {
			System.err.println("Usuário tentou roletar sem a informação necessária");
			
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print("Sem todos os parametros necessários.");
			return;
		}
		
		Long id;
		BigDecimal bet;
		int choice;
		
		try {
			id = Long.parseLong(sid);
			bet = new BigDecimal(sbet);
			choice  = Roleta.resToInt(Roleta.RoletaRes.valueOf(cor));
		} catch(Exception e) {
			System.err.println("Usuário passou argumentos inválidos");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		
		Conta jogador = ContasDAO.getInstance(getContaPath()).accessId(id);
		
		if(jogador == null) {
			System.err.println("Usuário tentou roletar com id inexistente");
			
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print("Id não existe.");
			return;
		}
		
		if(!jogador.canBet(bet)) {
			System.err.println("Usuário tentou roletar sem dinheiro");
			
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print("Dinheiro insuficiente.");
			return;
		}
		
		
		PrintWriter pr = response.getWriter();
		Jogo jogo = new Roleta();
		
		System.out.println("Upd Jogador: " + (((User) jogador).toString()));
		pr.write(jogo.play(bet, choice, jogador));		
		
		ContasDAO.getInstance(getContaPath()).persist();
	}

}
