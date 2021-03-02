package br.com.ledscolatina.backend.controller;
import br.com.ledscolatina.backend.model.Cliente;
import br.com.ledscolatina.backend.model.dto.Cliente.ClienteDTO;
import br.com.ledscolatina.backend.service.ClienteService;
import br.com.ledscolatina.backend.util.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(clienteService.index());
    }

    @PostMapping
    public ResponseEntity<?> create(@DTO(ClienteDTO.class) Cliente cliente) {
        return ResponseEntity.ok(clienteService.create(cliente));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.show(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @DTO(ClienteDTO.class) Cliente cliente) {
        cliente.setId(id);
        return ResponseEntity.ok(clienteService.update(cliente));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
