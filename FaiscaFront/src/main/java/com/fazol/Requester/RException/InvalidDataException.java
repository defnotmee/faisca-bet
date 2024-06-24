package com.fazol.Requester.RException;

public class InvalidDataException extends Exception{
    public InvalidDataException(String errorMessage) {
        super(errorMessage);
    }
}