package br.com.ledscolatina.backend.except.custom;

import lombok.Getter;

@Getter
public class TituloNotFoundException extends RuntimeException {

    public TituloNotFoundException() {
    }
}
