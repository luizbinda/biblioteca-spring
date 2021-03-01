package br.com.ledscolatina.backend.service;

import br.com.ledscolatina.backend.except.custom.ClasseNotFoundException;
import br.com.ledscolatina.backend.model.Classe;
import br.com.ledscolatina.backend.model.dto.classe.ClasseDTO;
import br.com.ledscolatina.backend.model.dto.classe.ClasseIndexDTO;
import br.com.ledscolatina.backend.repository.ClasseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClasseService {

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ClasseDTO create(Classe classe) {
        return modelMapper.map(classeRepository.save(classe), ClasseDTO.class);
    }

    public List<ClasseIndexDTO> index() {
        return classeRepository.findAll().stream()
                .map(classe -> modelMapper.map(classe, ClasseIndexDTO.class))
                .collect(Collectors.toList());
    }

    public ClasseIndexDTO show(Long id) {
        return classeRepository.findById(id)
                .map(record -> modelMapper.map(record, ClasseIndexDTO.class))
                .orElseThrow(() -> new ClasseNotFoundException(id));
    }

    public ClasseIndexDTO update(Classe classe) {
        return classeRepository.findById(classe.getId())
                .map(record -> {
                    record.setNome(classe.getNome());
                    record.setUpdatedAt(classe.getUpdatedAt());
                    return modelMapper.map(classeRepository.save(record), ClasseIndexDTO.class);
                }).orElseThrow(() -> new ClasseNotFoundException(classe.getId()));
    }

    public void delete(Long id) {
        if (classeRepository.findById(id).isPresent()) {
            classeRepository.deleteById(id);
        }
        else {
            throw new ClasseNotFoundException(id);
        }
    }

}
