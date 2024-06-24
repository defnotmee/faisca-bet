package br.com.FaiscaAPI;

import javax.servlet.http.HttpServlet;

public abstract class FaiscaServlet extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;

	protected String getContaPath() {
		return "FaiscaDatabase/contas.ser";
	}
}