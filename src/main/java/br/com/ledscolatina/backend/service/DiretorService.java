package br.com.ledscolatina.backend.service;

import br.com.ledscolatina.backend.except.custom.DiretorNotFoundException;
import br.com.ledscolatina.backend.model.Diretor;
import br.com.ledscolatina.backend.model.dto.Diretor.DiretorDTO;
import br.com.ledscolatina.backend.model.dto.Diretor.DiretorIndexDTO;
import br.com.ledscolatina.backend.repository.DiretorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository diretorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public DiretorDTO create(Diretor diretor) {
        return modelMapper.map(diretorRepository.save(diretor), DiretorDTO.class);
    }

    public List<DiretorIndexDTO> index() {
        return diretorRepository.findAll().stream()
                .map(diretor -> modelMapper.map(diretor, DiretorIndexDTO.class))
                .collect(Collectors.toList());
    }

    public DiretorIndexDTO show(Long id) {
        return diretorRepository.findById(id)
                .map(record -> modelMapper.map(record, DiretorIndexDTO.class))
                .orElseThrow(() -> new DiretorNotFoundException(id));
    }

    public DiretorIndexDTO update(Diretor diretor) {
        return diretorRepository.findById(diretor.getId())
                .map(record -> {
                    record.setNome(diretor.getNome());
                    record.setUpdatedAt(diretor.getUpdatedAt());
                    return modelMapper.map(diretorRepository.save(record), DiretorIndexDTO.class);
                }).orElseThrow(() -> new DiretorNotFoundException(diretor.getId()));
    }

    public void delete(Long id) {
        if (diretorRepository.findById(id).isPresent()) {
            diretorRepository.deleteById(id);
        }
        else {
            throw new DiretorNotFoundException(id);
        }
    }

}
