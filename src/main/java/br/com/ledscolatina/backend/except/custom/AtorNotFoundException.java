package br.com.ledscolatina.backend.except.custom;

import lombok.Getter;

@Getter
public class AtorNotFoundException extends RuntimeException {

    public AtorNotFoundException() {
    }
}
