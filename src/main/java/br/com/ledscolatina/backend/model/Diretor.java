package br.com.ledscolatina.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity(name="diretores")
@Getter @Setter
public class Diretor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O Nome do Diretor é obrigatorio.")
    @Size(min = 1, max = 255, message = "O Nome do Diretor deve conter entre 1 e 255 caracteres.")
    private String nome;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
