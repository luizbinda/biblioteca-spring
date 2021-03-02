package br.com.ledscolatina.backend.service;

import br.com.ledscolatina.backend.except.custom.AtorNotFoundException;
import br.com.ledscolatina.backend.model.Ator;
import br.com.ledscolatina.backend.model.dto.Ator.AtorDTO;
import br.com.ledscolatina.backend.repository.AtorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AtorDTO create(Ator ator) {
        return modelMapper.map(atorRepository.save(ator), AtorDTO.class);
    }

    public List<AtorDTO> index() {
        return atorRepository.findAll().stream()
                .map(ator -> modelMapper.map(ator, AtorDTO.class))
                .collect(Collectors.toList());
    }

    public AtorDTO show(Long id) {
        return atorRepository.findById(id)
                .map(record -> modelMapper.map(record, AtorDTO.class))
                .orElseThrow(() -> new AtorNotFoundException(id));
    }

    public AtorDTO update(Ator ator) {
        return atorRepository.findById(ator.getId())
                .map(record -> {
                    record.setNome(ator.getNome());
                    record.setUpdatedAt(ator.getUpdatedAt());
                    return modelMapper.map(atorRepository.save(record), AtorDTO.class);
                }).orElseThrow(() -> new AtorNotFoundException(ator.getId()));
    }

    public void delete(Long id) {
        if (atorRepository.findById(id).isPresent()) {
            atorRepository.deleteById(id);
        }
        else {
            throw new AtorNotFoundException(id);
        }
    }

}
