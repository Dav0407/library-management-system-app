package com.igriss.library_manager.exception;

public class OperationNotPermittedException extends RuntimeException {

    public OperationNotPermittedException(String message) {
        super(message);
    }
}
