package br.com.Faisca.Contas;

public class PermissionException extends Exception {
	private static final long serialVersionUID = 1L;

	public PermissionException(String errorMessage) {
        super(errorMessage);
    }
}
