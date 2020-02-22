package com.epam.lab.exception;

public class InsufficientEntityDataException extends RuntimeException {

    public InsufficientEntityDataException() {
        super();
    }

    public InsufficientEntityDataException(String s) {
        super(s);
    }

    public InsufficientEntityDataException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InsufficientEntityDataException(Throwable throwable) {
        super(throwable);
    }
}
