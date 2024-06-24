package com.fazol.Requester.RException;

public class InvalidCpfException extends Exception{
    public InvalidCpfException(String errorMessage) {
        super(errorMessage);
    }
}