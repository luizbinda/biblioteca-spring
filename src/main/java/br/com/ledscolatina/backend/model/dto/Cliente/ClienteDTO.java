package br.com.ledscolatina.backend.model.dto.Cliente;

import br.com.ledscolatina.backend.model.Cliente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private String nome;
    private String sexo;
    private String endereco;
    private String telefone;
    private String cpf;
    private Boolean ativo = true;
    private Date data_nascimento;
    private Cliente cliente;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final LocalDateTime updatedAt = LocalDateTime.now();
}
