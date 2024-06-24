package br.com.FaiscaAPI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.Faisca.Contas.Conta;
import br.com.Faisca.Contas.ContasDAO;

/**
 * id
 */
@WebServlet("/info")
public class ServletInfo extends FaiscaServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("id");
		
		if(sid == null) {
			System.err.println("Usuário tentou coletar informação sem id");
			
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print("Sem todos os parametros necessários.");
			return;
		}
		Long id;
		
		try { 	
			id = Long.parseLong(sid);
		} catch(Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print("Mal formulado.");
			return;
		}
		
		Conta usuario = ContasDAO.getInstance(getContaPath()).accessId(id);
		
		if(usuario == null) {
			System.err.println("A conta com o id requerido pelo usuário não foi encontrada");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().print("Sem conta com esse id.");
			return;
		}
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(usuario);
		
		response.getWriter().print(json);
		
		System.out.println("Foi requerido o seguinte usuario: " + json);
		
	}

}
