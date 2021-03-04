package br.com.ledscolatina.backend.service;

import br.com.ledscolatina.backend.except.custom.AtorInTituloException;
import br.com.ledscolatina.backend.except.custom.ClasseInTituloException;
import br.com.ledscolatina.backend.except.custom.ClasseNotFoundException;
import br.com.ledscolatina.backend.model.Classe;
import br.com.ledscolatina.backend.model.dto.classe.ClasseDTO;
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

    public List<ClasseDTO> index() {
        return classeRepository.findAll().stream()
                .map(classe -> modelMapper.map(classe, ClasseDTO.class))
                .collect(Collectors.toList());
    }

    public ClasseDTO show(Long id) {
        return classeRepository.findById(id)
                .map(record -> modelMapper.map(record, ClasseDTO.class))
                .orElseThrow(() -> new ClasseNotFoundException(id));
    }

    public ClasseDTO update(Classe classe) {
        return classeRepository.findById(classe.getId())
                .map(record -> {
                    record.setNome(classe.getNome());
                    record.setValor(classe.getValor());
                    record.setPrazo_devolucao(classe.getPrazo_devolucao());
                    record.setUpdatedAt(classe.getUpdatedAt());
                    return modelMapper.map(classeRepository.save(record), ClasseDTO.class);
                }).orElseThrow(() -> new ClasseNotFoundException(classe.getId()));
    }

    public void delete(Long id) {
        if (classeRepository.findById(id).isPresent()) {
            try {
                classeRepository.deleteById(id);
            } catch (Exception e) {
                throw new ClasseInTituloException();
            }
        }
        else {
            throw new ClasseNotFoundException(id);
        }
    }

}
