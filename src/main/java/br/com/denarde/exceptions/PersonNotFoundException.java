package br.com.denarde.exceptions;

public class PersonNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Person with id: %s not found.";

    public PersonNotFoundException(String id) {
        super(String.format(MESSAGE, id));
    }
}
