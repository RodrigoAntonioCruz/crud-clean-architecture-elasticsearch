package br.com.example.core.usecase;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String object) {
        super(object);
    }

}
