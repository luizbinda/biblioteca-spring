package br.com.ledscolatina.backend.controller;
import br.com.ledscolatina.backend.model.Locacao;
import br.com.ledscolatina.backend.model.dto.Locacao.LocacaoDTO;
import br.com.ledscolatina.backend.service.LocacaoService;
import br.com.ledscolatina.backend.util.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("locacao")
public class LocacaoController {

    @Autowired
    private LocacaoService locacaoService;

    @GetMapping
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(locacaoService.index());
    }

    @PostMapping
    public ResponseEntity<?> create(@DTO(LocacaoDTO.class) Locacao locacao) {
        return ResponseEntity.ok(locacaoService.create(locacao));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        return ResponseEntity.ok(locacaoService.show(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @DTO(LocacaoDTO.class) Locacao locacao) {
        locacao.setId(id);
        return ResponseEntity.ok(locacaoService.update(locacao));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        locacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
