package br.com.ledscolatina.backend.model.dto.Titulo;

import br.com.ledscolatina.backend.model.dto.Ator.AtorDTO;
import br.com.ledscolatina.backend.model.dto.AtorTituloDTO;
import br.com.ledscolatina.backend.model.dto.Diretor.DiretorDTO;
import br.com.ledscolatina.backend.model.dto.classe.ClasseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TituloDTO {
    private Long id;
    private String nome;
    private String sinopse;
    private String categoria;
    private Date ano;
    private DiretorDTO diretor;
    private ClasseDTO classe;
    private List<AtorDTO> atores;
    private List<AtorTituloDTO> atores_titulo;
}
