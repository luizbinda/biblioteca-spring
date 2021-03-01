package br.com.ledscolatina.backend.except.custom;

import lombok.Getter;

@Getter
public class TituloNotFoundException extends RuntimeException {

    private Long idBuscado;

    public TituloNotFoundException(Long idBuscado) {
        this.idBuscado = idBuscado;
    }
}
