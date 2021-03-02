package br.com.ledscolatina.backend.model.dto.Locacao;

import br.com.ledscolatina.backend.model.Cliente;
import br.com.ledscolatina.backend.model.Item;
import br.com.ledscolatina.backend.model.dto.Ator.AtorDTO;
import br.com.ledscolatina.backend.model.dto.AtorTituloDTO;
import br.com.ledscolatina.backend.model.dto.Diretor.DiretorDTO;
import br.com.ledscolatina.backend.model.dto.classe.ClasseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class LocacaoDTO {
    private Long id;
    private Double valor;
    private Double multa;
    private Item item;
    private Cliente cliente;
    private LocalDateTime data_locacao;
    private LocalDateTime data_devolucao_prevista;
    private LocalDateTime data_devolucao_efetiva;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final LocalDateTime updatedAt = LocalDateTime.now();
}
