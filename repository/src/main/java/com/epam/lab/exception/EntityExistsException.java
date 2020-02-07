package com.epam.lab.exception;

public class EntityExistsException extends RuntimeException {
    public EntityExistsException() {
        super();
    }

    public EntityExistsException(String s) {
        super(s);
    }

    public EntityExistsException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public EntityExistsException(Throwable throwable) {
        super(throwable);
    }
}
