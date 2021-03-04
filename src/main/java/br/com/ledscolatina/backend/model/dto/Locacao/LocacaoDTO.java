package br.com.ledscolatina.backend.model.dto.Locacao;

import br.com.ledscolatina.backend.model.dto.Cliente.ClienteDTO;
import br.com.ledscolatina.backend.model.dto.Item.ItemDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class LocacaoDTO {
    private Long id;
    private Double valor;
    private Double multa;
    private ItemDTO item;
    private ClienteDTO cliente;
    private LocalDateTime data_locacao;
    private LocalDateTime data_devolucao_prevista;
    private LocalDateTime data_devolucao_efetiva;
}
