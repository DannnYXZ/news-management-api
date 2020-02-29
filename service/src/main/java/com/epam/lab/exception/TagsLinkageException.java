package com.epam.lab.exception;

import java.util.ArrayList;
import java.util.List;

public class TagsLinkageException extends RuntimeException {

    private List<Exception> exceptions;

    public TagsLinkageException() {
        super();
        exceptions = new ArrayList<>();
    }

    public void add(Exception e) {
        exceptions.add(e);
    }

    public List<Exception> getExceptions() {
        return exceptions;
    }

    public TagsLinkageException(String s) {
        super(s);
    }

    public TagsLinkageException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public TagsLinkageException(Throwable throwable) {
        super(throwable);
    }
}
