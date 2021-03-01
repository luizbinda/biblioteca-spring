package br.com.ledscolatina.backend.model.dto.Titulo;

import br.com.ledscolatina.backend.model.dto.Ator.AtorIndexDTO;
import br.com.ledscolatina.backend.model.dto.AtorTituloDTO;
import br.com.ledscolatina.backend.model.dto.Diretor.DiretorIndexDTO;
import br.com.ledscolatina.backend.model.dto.classe.ClasseIndexDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TituloDTO {
    private String nome;
    private String sinopse;
    private String categoria;
    private Date ano;
    private DiretorIndexDTO diretor;
    private ClasseIndexDTO classe;
    private List<AtorIndexDTO> atores;
    private List<AtorTituloDTO> atores_titulo;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final LocalDateTime updatedAt = LocalDateTime.now();
}
