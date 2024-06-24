package br.com.FaiscaAPI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
	    System.out.println(getDataPath());
		
		String senha = request.getParameter("senha");
		
		if(senha.equals("FelipeNetoOCara")) {
			ContasDAO.getInstance(getDataPath()).persist();
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
			
	}

}
