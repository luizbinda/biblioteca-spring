package br.com.ledscolatina.backend.except.custom;

import lombok.Getter;

@Getter
public class ClienteNotActiveException extends RuntimeException {

    private final Long idBuscado;

    public ClienteNotActiveException(Long idBuscado) {
        this.idBuscado = idBuscado;
    }
}
