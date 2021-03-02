package br.com.ledscolatina.backend.except.custom;

import lombok.Getter;

@Getter
public class ClienteNotFoundException extends RuntimeException {

    private final Long idBuscado;

    public ClienteNotFoundException(Long idBuscado) {
        this.idBuscado = idBuscado;
    }
}
