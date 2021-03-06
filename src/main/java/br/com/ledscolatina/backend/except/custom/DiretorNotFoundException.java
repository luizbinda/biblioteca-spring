package br.com.ledscolatina.backend.except.custom;

import lombok.Getter;

@Getter
public class DiretorNotFoundException extends RuntimeException {

    private final Long idBuscado;

    public DiretorNotFoundException(Long idBuscado) {
        this.idBuscado = idBuscado;
    }
}
