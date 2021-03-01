package br.com.ledscolatina.backend.except.custom;

import lombok.Getter;

@Getter
public class ClasseNotFoundException extends RuntimeException {

    private Long idBuscado;

    public ClasseNotFoundException(Long idBuscado) {
        this.idBuscado = idBuscado;
    }
}
