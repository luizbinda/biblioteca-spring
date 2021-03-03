package br.com.ledscolatina.backend.except.custom;

import lombok.Getter;

@Getter
public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException() {

    }
}
