package com.fazol.Requester.RException;

public class TooPoorException extends PermissionException {
	public TooPoorException(String errorMessage) {
        super(errorMessage);
    }
}
