package br.com.ledscolatina.backend.service;

import br.com.ledscolatina.backend.except.custom.TituloNotFoundException;
import br.com.ledscolatina.backend.model.AtorTitulo;
import br.com.ledscolatina.backend.model.Titulo;
import br.com.ledscolatina.backend.model.dto.Titulo.TituloDTO;
import br.com.ledscolatina.backend.model.dto.Titulo.TituloIndexDTO;
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
        TituloIndexDTO response =  modelMapper.map(tituloRepository.save(titulo), TituloIndexDTO.class);
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

    public List<TituloIndexDTO> index() {
        return tituloRepository.findAll().stream()
                .map(titulo -> modelMapper.map(titulo, TituloIndexDTO.class))
                .collect(Collectors.toList());
    }

    public TituloIndexDTO show(Long id) {
        return tituloRepository.findById(id)
                .map(record -> modelMapper.map(record, TituloIndexDTO.class))
                .orElseThrow(() -> new TituloNotFoundException(id));
    }

    public TituloIndexDTO update(Titulo titulo) {
        return tituloRepository.findById(titulo.getId())
                .map(record -> {
                    record.setNome(titulo.getNome());
                    record.setUpdatedAt(titulo.getUpdatedAt());
                    return modelMapper.map(tituloRepository.save(record), TituloIndexDTO.class);
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
