package com.system.pharmacy.exceptions;

public class MedicineSoldOutException extends RuntimeException {
    public MedicineSoldOutException(String message) {
        super(message);
    }
}
