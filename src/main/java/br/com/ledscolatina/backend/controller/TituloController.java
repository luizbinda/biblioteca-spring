package br.com.ledscolatina.backend.controller;
import br.com.ledscolatina.backend.model.Titulo;
import br.com.ledscolatina.backend.model.dto.Titulo.TituloDTO;
import br.com.ledscolatina.backend.model.dto.Titulo.TituloIndexDTO;
import br.com.ledscolatina.backend.service.TituloService;
import br.com.ledscolatina.backend.util.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("titulo")
public class TituloController {

    @Autowired
    private TituloService tituloService;

    @GetMapping
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(tituloService.index());
    }

    @PostMapping
    public ResponseEntity<?> create(@DTO(TituloDTO.class) Titulo titulo) {
        return ResponseEntity.ok(tituloService.create(titulo));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        return ResponseEntity.ok(tituloService.show(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @DTO(TituloIndexDTO.class) Titulo titulo) {
        titulo.setId(id);
        return ResponseEntity.ok(tituloService.update(titulo));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tituloService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
