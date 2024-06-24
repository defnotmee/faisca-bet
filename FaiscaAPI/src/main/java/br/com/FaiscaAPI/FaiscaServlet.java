package br.com.FaiscaAPI;

import javax.servlet.http.HttpServlet;

public abstract class FaiscaServlet extends HttpServlet  {
	
	protected String getContaPath() {
		return "FaiscaDatabase/contas.ser";
	}
}