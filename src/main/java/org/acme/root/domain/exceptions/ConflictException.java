package org.acme.root.domain.exceptions;

public class ConflictException  extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
