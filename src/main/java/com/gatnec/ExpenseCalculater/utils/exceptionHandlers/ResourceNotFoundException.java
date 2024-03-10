package com.gatnec.ExpenseCalculater.utils.exceptionHandlers;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
