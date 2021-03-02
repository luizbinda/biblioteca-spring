package br.com.ledscolatina.backend.service;

import br.com.ledscolatina.backend.except.custom.TituloNotFoundException;
import br.com.ledscolatina.backend.model.AtorTitulo;
import br.com.ledscolatina.backend.model.Titulo;
import br.com.ledscolatina.backend.model.dto.Titulo.TituloDTO;
import br.com.ledscolatina.backend.repository.AtorTituloRepository;
import br.com.ledscolatina.backend.repository.TituloRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        TituloDTO response =  modelMapper.map(tituloRepository.save(titulo), TituloDTO.class);
        List<AtorTitulo> atores = new ArrayList<>();
        titulo.getAtores().forEach( ator -> {
            AtorTitulo atorTitulo = new AtorTitulo();
            atorTitulo.setAtor(ator);
            titulo.setId(response.getId());
            atorTitulo.setTitulo(titulo);
            atores.add(atorTitulo);
        });
        ator_tituloRepository.saveAll(atores);
        return response;

    }

    public List<TituloDTO> index() {
        return tituloRepository.findAll().stream()
                .map(titulo -> modelMapper.map(titulo, TituloDTO.class))
                .collect(Collectors.toList());
    }

    public TituloDTO show(Long id) {
        return tituloRepository.findById(id)
                .map(record -> modelMapper.map(record, TituloDTO.class))
                .orElseThrow(() -> new TituloNotFoundException(id));
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
                }).orElseThrow(() -> new TituloNotFoundException(titulo.getId()));
    }

    public void delete(Long id) {
        if (tituloRepository.findById(id).isPresent()) {
            tituloRepository.deleteById(id);
        }
        else {
            throw new TituloNotFoundException(id);
        }
    }

}
