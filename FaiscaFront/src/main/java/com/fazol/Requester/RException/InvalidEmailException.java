package com.fazol.Requester.RException;

public class InvalidEmailException extends Exception{
    public InvalidEmailException(String errorMessage) {
        super(errorMessage);
    }
}