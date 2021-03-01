package br.com.ledscolatina.backend.controller;
import br.com.ledscolatina.backend.model.Classe;
import br.com.ledscolatina.backend.model.dto.classe.ClasseDTO;
import br.com.ledscolatina.backend.model.dto.classe.ClasseIndexDTO;
import br.com.ledscolatina.backend.service.ClasseService;
import br.com.ledscolatina.backend.util.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("classe")
public class ClasseController {

    @Autowired
    private ClasseService classeService;

    @GetMapping
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(classeService.index());
    }

    @PostMapping
    public ResponseEntity<?> create(@DTO(ClasseDTO.class) Classe classe) {
        return ResponseEntity.ok(classeService.create(classe));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        return ResponseEntity.ok(classeService.show(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @DTO(ClasseIndexDTO.class) Classe classe) {
        classe.setId(id);
        return ResponseEntity.ok(classeService.update(classe));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        classeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
