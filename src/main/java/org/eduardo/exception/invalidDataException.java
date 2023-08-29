package org.eduardo.exception;

public class invalidDataException extends IllegalArgumentException{
    public invalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
