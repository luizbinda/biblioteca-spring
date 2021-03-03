package br.com.ledscolatina.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity(name="titulos")
@Getter @Setter
public class Titulo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O Nome do Titulo é obrigatorio.")
    @Size(min = 1, max = 255, message = "O Nome do Titulo deve conter entre 1 e 255 caracteres.")
    private String nome;

    @NotNull(message = "A sinopse do Titulo é obrigatorio.")
    @Size(min = 1, max = 255, message = "A sinopse do Titulo deve conter entre 1 e 255 caracteres.")
    private String sinopse;

    @NotNull(message = "A categoria do Titulo é obrigatorio.")
    @Size(min = 1, max = 255, message = "A categoria do Titulo deve conter entre 1 e 255 caracteres.")
    private String categoria;

    @NotNull(message = "O Ano do Titulo é obrigatorio.")
    private Date ano;

    @NotNull(message = "Todo Titulo deve pertencer a um diretor.")
    @ManyToOne
    @JoinColumn(name = "diretor_id")
    private Diretor diretor;

    @NotNull(message = "Todo Titulo deve pertencer a uma classe.")
    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    @ManyToMany()
    @JoinTable
            (
                name = "titulo_atores",
                joinColumns = {
                        @JoinColumn(name = "titulo_id")
                },
                inverseJoinColumns = {
                        @JoinColumn(name = "ator_id")}
            )
    private List<Ator> atores;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

}
