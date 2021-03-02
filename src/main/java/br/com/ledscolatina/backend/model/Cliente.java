package br.com.ledscolatina.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name="clientes")
@Getter @Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O Nome do Cliente é obrigatorio.")
    @Size(min = 1, max = 255, message = "O Número de Série do Item deve conter entre 1 e 255 caracteres.")
    private String nome;

    @NotNull(message = "O Sexo do Cliente é obrigatorio.")
    private String sexo;

    @Size(min = 1, max = 255, message = "O Número de Série do Item deve conter entre 1 e 255 caracteres.")
    private String endereco;

    @Size(min = 1, max = 255, message = "O Número de Série do Item deve conter entre 1 e 255 caracteres.")
    private String telefone;

    @Size(min = 1, max = 255, message = "O Número de Série do Item deve conter entre 1 e 255 caracteres.")
    private String cpf;

    private Boolean ativo = true;

    @NotNull(message = "A Data Aquisicao do Item é obrigatorio.")
    private Date data_nascimento;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
