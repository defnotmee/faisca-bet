package com.fazol.Requester.RException;

public class InvalidEmailException extends InvalidDataException{
    public InvalidEmailException(String errorMessage) {
        super(errorMessage);
    }
}