package br.com.ledscolatina.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name="locacoes")
@Getter @Setter
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O Valor da Locacoes é obrigatorio.")
    private Double valor;

    @NotNull(message = "A Multa da Locacoes é obrigatorio.")
    private Double multa;

    @NotNull(message = "O Item da Locacoes é obrigatorio.")
    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    @NotNull(message = "O Cliente da Locacoes é obrigatorio.")
    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @NotNull(message = "A Data da Locacoes é obrigatorio.")
    private LocalDateTime data_locacao;

    @NotNull(message = "A Data de devoluca da Locacoes é obrigatorio.")
    private LocalDateTime data_devolucao_prevista;

    private LocalDateTime data_devolucao_efetiva;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
