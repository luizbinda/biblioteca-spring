package br.com.ledscolatina.backend.controller;
import br.com.ledscolatina.backend.model.Ator;
import br.com.ledscolatina.backend.model.dto.Ator.AtorDTO;
import br.com.ledscolatina.backend.model.dto.Ator.AtorIndexDTO;
import br.com.ledscolatina.backend.service.AtorService;
import br.com.ledscolatina.backend.util.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("atores")
public class AtorController {

    @Autowired
    private AtorService atorService;

    @GetMapping
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(atorService.index());
    }

    @PostMapping
    public ResponseEntity<?> create(@DTO(AtorDTO.class) Ator ator) {
        return ResponseEntity.ok(atorService.create(ator));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        return ResponseEntity.ok(atorService.show(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @DTO(AtorIndexDTO.class) Ator ator) {
        ator.setId(id);
        return ResponseEntity.ok(atorService.update(ator));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        atorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
