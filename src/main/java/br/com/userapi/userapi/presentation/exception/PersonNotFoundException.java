package br.com.userapi.userapi.presentation.exception;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String message) {
        super(message);
    }
}