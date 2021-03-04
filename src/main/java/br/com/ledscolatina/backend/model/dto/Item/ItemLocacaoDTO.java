package br.com.ledscolatina.backend.model.dto.Item;


import br.com.ledscolatina.backend.model.dto.Locacao.LocacaoDTO;
import br.com.ledscolatina.backend.model.dto.Titulo.TituloDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ItemLocacaoDTO {
    private Long id;
    private String numSerie;
    private TituloDTO titulo;
    private List<LocacaoDTO> locacao;
    private Date data_aquisicao;
    private String tipo;
}
