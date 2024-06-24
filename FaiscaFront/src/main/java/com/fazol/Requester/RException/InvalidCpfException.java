package com.fazol.Requester.RException;

public class InvalidCpfException extends InvalidDataException{
    public InvalidCpfException(String errorMessage) {
        super(errorMessage);
    }
}