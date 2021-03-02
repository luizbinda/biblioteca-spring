package br.com.ledscolatina.backend.except.custom;

import lombok.Getter;

@Getter
public class ItemLocadoException extends RuntimeException {

    private final String message;

    public ItemLocadoException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
