package br.com.FaiscaAPI;

import javax.servlet.http.HttpServlet;

public abstract class FaiscaServlet extends HttpServlet  {
	
	protected String getDataPath() {
		return getServletContext().getRealPath("/Database/contas.ser");
	}
}
