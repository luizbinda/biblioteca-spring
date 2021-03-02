package br.com.ledscolatina.backend.model.dto.Item;


import br.com.ledscolatina.backend.model.Locacao;
import br.com.ledscolatina.backend.model.Titulo;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ItemDTO {
    private Long id;
    private String numSerie;
    private Titulo titulo;
    private Date data_aquisicao;
    private String tipo;
    private Locacao locacao;

    private final LocalDateTime createdAt = LocalDateTime.now();
    private final LocalDateTime updatedAt = LocalDateTime.now();
}
