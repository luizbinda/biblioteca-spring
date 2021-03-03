package br.com.ledscolatina.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity(name="itens")
@Getter @Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O Número de Série do Item é obrigatorio.")
    @Size(min = 1, max = 255, message = "O Número de Série do Item deve conter entre 1 e 255 caracteres.")
    private String numSerie;

    @NotNull(message = "O Titulo do Item é obrigatorio.")
    @ManyToOne
    @JoinColumn(name="titulo_id")
    private Titulo titulo;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<Locacao> locacao;

    @NotNull(message = "A Data Aquisicao do Item é obrigatorio.")
    private Date data_aquisicao;

    @NotNull(message = "O Tipo do Item é obrigatorio.")
    @Size(min = 1, max = 255, message = "O Tipo do Item deve conter entre 1 e 255 caracteres.")
    private String tipo;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
