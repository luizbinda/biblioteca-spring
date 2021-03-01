package br.com.ledscolatina.backend.model.dto;

import br.com.ledscolatina.backend.model.Ator;
import br.com.ledscolatina.backend.model.Titulo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AtorTituloDTO {
    private Ator ator;
    private Titulo titulo;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final LocalDateTime updatedAt = LocalDateTime.now();
}
