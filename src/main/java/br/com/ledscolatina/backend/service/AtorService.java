package br.com.ledscolatina.backend.service;

import br.com.ledscolatina.backend.except.custom.CadernoNotFoundException;
import br.com.ledscolatina.backend.model.Ator;
import br.com.ledscolatina.backend.model.dto.Ator.AtorDTO;
import br.com.ledscolatina.backend.model.dto.Ator.AtorIndexDTO;
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

    public List<AtorIndexDTO> index() {
        return atorRepository.findAll().stream()
                .map(ator -> modelMapper.map(ator, AtorIndexDTO.class))
                .collect(Collectors.toList());
    }

    public AtorIndexDTO show(Long id) {
        return atorRepository.findById(id)
                .map(record -> modelMapper.map(record, AtorIndexDTO.class))
                .orElseThrow(() -> new CadernoNotFoundException(id));
    }

    public AtorIndexDTO update(Ator ator) {
        return atorRepository.findById(ator.getId())
                .map(record -> {
                    record.setNome(ator.getNome());
                    record.setUpdatedAt(ator.getUpdatedAt());
                    return modelMapper.map(atorRepository.save(record), AtorIndexDTO.class);
                }).orElseThrow(() -> new CadernoNotFoundException(ator.getId()));
    }

    public void delete(Long id) {
        if (atorRepository.findById(id).isPresent()) {
            atorRepository.deleteById(id);
        }
        else {
            throw new CadernoNotFoundException(id);
        }
    }

}
