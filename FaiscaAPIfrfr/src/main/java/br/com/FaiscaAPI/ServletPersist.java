package br.com.FaiscaAPI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.Faisca.Contas.ContasDAO;

/**
 * Servlet implementation class ServletPersist
 */
@WebServlet("/persist")
public class ServletPersist extends FaiscaServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPersist() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String json = "{\n"
				+ "  \"nome\" : \"a\",\n"
				+ "  \"hashSenha\" : \"ypeBEsobvcr6wjGzmiPcTaeG7/gUfE5yuYB3ha/uSLs=\",\n"
				+ "  \"id\" : -4681631070730195791,\n"
				+ "  \"cpf\" : \"null\",\n"
				+ "  \"email\" : \"a@aa.com\",\n"
				+ "  \"balance\" : 0,\n"
				+ "  \"bonusAvailable\" : false\n"
				+ "}";
		*/
		
		//final JsonNode node = new ObjectMapper().readTree(json);
		
		//System.out.println("id: " + node.get("id"));
		//System.out.println(node.get("balance").getClass().getSimpleName());
		
		String senha = request.getParameter("senha");

		if(senha.equals("FelipeNetoOCara")) {
			ContasDAO.getInstance(getContaPath()).persist();
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
			
	}

}
