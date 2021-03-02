package br.com.ledscolatina.backend.except.custom;

import lombok.Getter;

@Getter
public class ItemNotFoundException extends RuntimeException {

    private final Long idBuscado;

    public ItemNotFoundException(Long idBuscado) {
        this.idBuscado = idBuscado;
    }
}
