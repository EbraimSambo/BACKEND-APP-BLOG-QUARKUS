package org.acme.root.domain.exceptions;

public class NotfoundException  extends RuntimeException {
    public NotfoundException(String message) {
        super(message);
    }
}
