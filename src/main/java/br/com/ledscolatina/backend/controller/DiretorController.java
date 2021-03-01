package br.com.ledscolatina.backend.controller;
import br.com.ledscolatina.backend.model.Diretor;
import br.com.ledscolatina.backend.model.dto.Diretor.DiretorDTO;
import br.com.ledscolatina.backend.model.dto.Diretor.DiretorIndexDTO;
import br.com.ledscolatina.backend.service.DiretorService;
import br.com.ledscolatina.backend.service.DiretorService;
import br.com.ledscolatina.backend.util.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("diretor")
public class DiretorController {

    @Autowired
    private DiretorService diretorService;

    @GetMapping
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(diretorService.index());
    }

    @PostMapping
    public ResponseEntity<?> create(@DTO(DiretorDTO.class) Diretor ator) {
        return ResponseEntity.ok(diretorService.create(ator));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        return ResponseEntity.ok(diretorService.show(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @DTO(DiretorIndexDTO.class) Diretor ator) {
        ator.setId(id);
        return ResponseEntity.ok(diretorService.update(ator));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        diretorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
