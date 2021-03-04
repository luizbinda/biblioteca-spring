package br.com.ledscolatina.backend.model.dto.Item;


import br.com.ledscolatina.backend.model.dto.Titulo.TituloDTO;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ItemDTO {
    private Long id;
    private String numSerie;
    private TituloDTO titulo;
    private Date data_aquisicao;
    private String tipo;
}
