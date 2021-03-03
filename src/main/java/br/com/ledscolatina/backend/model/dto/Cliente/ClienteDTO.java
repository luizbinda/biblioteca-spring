package br.com.ledscolatina.backend.model.dto.Cliente;

import lombok.Getter;
import lombok.Setter;

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
    private ClienteDTO cliente;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final LocalDateTime updatedAt = LocalDateTime.now();
}
