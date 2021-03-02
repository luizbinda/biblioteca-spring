package br.com.ledscolatina.backend.except.custom;

import lombok.Getter;

@Getter
public class AtorNotFoundException extends RuntimeException {

    private final Long idBuscado;

    public AtorNotFoundException(Long idBuscado) {
        this.idBuscado = idBuscado;
    }
}
