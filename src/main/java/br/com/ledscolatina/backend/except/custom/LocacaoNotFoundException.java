package br.com.ledscolatina.backend.except.custom;

import lombok.Getter;

@Getter
public class LocacaoNotFoundException extends RuntimeException {

    private final Long idBuscado;

    public LocacaoNotFoundException(Long idBuscado) {
        this.idBuscado = idBuscado;
    }
}
