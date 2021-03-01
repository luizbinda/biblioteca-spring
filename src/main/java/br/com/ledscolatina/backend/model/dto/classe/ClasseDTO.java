package br.com.ledscolatina.backend.model.dto.classe;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ClasseDTO {
    private String nome;
    private Double valor;
    private Integer prazo_devolucao;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final LocalDateTime updatedAt = LocalDateTime.now();
}
