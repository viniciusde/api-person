package br.com.denarde.exceptions;

public class PersonAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE = "Person with id: %s already exists.";

    public PersonAlreadyExistsException(String id) {
        super(String.format(MESSAGE, id));
    }
}
