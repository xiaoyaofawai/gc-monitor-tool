package org.wbx.gcmonitor.exception;

public class DuplicateRegistrationException extends RuntimeException {

    public DuplicateRegistrationException() {
    }

    public DuplicateRegistrationException(String message) {
        super(message);
    }
}
