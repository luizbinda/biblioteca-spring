package br.com.ledscolatina.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity(name="titulo_atores")
@Getter @Setter
public class AtorTitulo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull()
    @ManyToOne
    @JoinColumn(name = "ator_id")
    private Ator ator;

    @NotNull()
    @ManyToOne
    @JoinColumn(name = "titulo_id")
    private Titulo titulo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
