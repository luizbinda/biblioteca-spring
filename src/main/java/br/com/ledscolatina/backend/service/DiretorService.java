package br.com.ledscolatina.backend.service;

import br.com.ledscolatina.backend.except.custom.ClasseInTituloException;
import br.com.ledscolatina.backend.except.custom.DiretorInTituloException;
import br.com.ledscolatina.backend.except.custom.DiretorNotFoundException;
import br.com.ledscolatina.backend.model.Diretor;
import br.com.ledscolatina.backend.model.dto.Diretor.DiretorDTO;
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

    public List<DiretorDTO> index() {
        return diretorRepository.findAll().stream()
                .map(diretor -> modelMapper.map(diretor, DiretorDTO.class))
                .collect(Collectors.toList());
    }

    public DiretorDTO show(Long id) {
        return diretorRepository.findById(id)
                .map(record -> modelMapper.map(record, DiretorDTO.class))
                .orElseThrow(() -> new DiretorNotFoundException(id));
    }

    public DiretorDTO update(Diretor diretor) {
        return diretorRepository.findById(diretor.getId())
                .map(record -> {
                    record.setNome(diretor.getNome());
                    record.setUpdatedAt(diretor.getUpdatedAt());
                    return modelMapper.map(diretorRepository.save(record), DiretorDTO.class);
                }).orElseThrow(() -> new DiretorNotFoundException(diretor.getId()));
    }

    public void delete(Long id) {
        if (diretorRepository.findById(id).isPresent()) {
            try {
                diretorRepository.deleteById(id);
            } catch (Exception e) {
                throw new DiretorInTituloException();
            }
        }
        else {
            throw new DiretorNotFoundException(id);
        }
    }

}
