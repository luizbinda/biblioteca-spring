package br.com.ledscolatina.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "classes")
@Getter @Setter
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double valor;
    private Integer prazo_devolucao;

    @NotNull(message = "O Nome da Classe é obrigatorio.")
    @Size(min = 1, max = 255, message = "O Nome da Classe deve conter entre 1 e 255 caracteres.")
    private String nome;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

}
