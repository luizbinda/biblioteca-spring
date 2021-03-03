package br.com.ledscolatina.backend.service;

import br.com.ledscolatina.backend.except.custom.TituloNotFoundException;
import br.com.ledscolatina.backend.model.Ator;
import br.com.ledscolatina.backend.model.AtorTitulo;
import br.com.ledscolatina.backend.model.Titulo;
import br.com.ledscolatina.backend.model.dto.Titulo.TituloDTO;
import br.com.ledscolatina.backend.repository.AtorTituloRepository;
import br.com.ledscolatina.backend.repository.TituloRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TituloService {

    @Autowired
    private TituloRepository tituloRepository;

    @Autowired
    private AtorTituloRepository ator_tituloRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TituloDTO create(Titulo titulo) {
        titulo.setCreatedAt(LocalDateTime.now());
        titulo.setUpdatedAt(LocalDateTime.now());
        return  modelMapper.map(tituloRepository.save(titulo), TituloDTO.class);
    }

    public List<TituloDTO> index() {
        return tituloRepository.findAll().stream()
                .map(titulo -> modelMapper.map(titulo, TituloDTO.class))
                .collect(Collectors.toList());
    }

    public TituloDTO show(Long id) {
        return tituloRepository.findById(id)
                .map(record -> modelMapper.map(record, TituloDTO.class))
                .orElseThrow(TituloNotFoundException::new);
    }

    public List<Titulo> showAtor(Long id) {
        List<Titulo> titulo =  tituloRepository.findByAtoresId(id);
        if (titulo == null ) {
            throw new TituloNotFoundException();
        }

        return titulo;
    }

    public List<Titulo> show(String categoria) {
        List<Titulo> titulo =  tituloRepository.findByCategoria(categoria);
        if (titulo == null ) {
            throw new TituloNotFoundException();
        }

        return titulo;
    }

    public TituloDTO update(Titulo titulo) {
        return tituloRepository.findById(titulo.getId())
                .map(record -> {
                    record.setNome(titulo.getNome());
                    record.setDiretor(titulo.getDiretor());
                    record.setAtores(titulo.getAtores());
                    record.setAno(titulo.getAno());
                    record.setSinopse(titulo.getSinopse());
                    record.setCategoria(titulo.getCategoria());
                    record.setClasse(titulo.getClasse());
                    record.setUpdatedAt(titulo.getUpdatedAt());
                    return modelMapper.map(tituloRepository.save(record), TituloDTO.class);
                }).orElseThrow(TituloNotFoundException::new);
    }

    public void delete(Long id) {
        if (tituloRepository.findById(id).isPresent()) {
            tituloRepository.deleteById(id);
        }
        else {
            throw new TituloNotFoundException();
        }
    }

}
