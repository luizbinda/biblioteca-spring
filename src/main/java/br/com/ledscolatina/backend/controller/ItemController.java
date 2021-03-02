package br.com.ledscolatina.backend.controller;
import br.com.ledscolatina.backend.model.Item;
import br.com.ledscolatina.backend.model.dto.Item.ItemDTO;
import br.com.ledscolatina.backend.service.ItemService;
import br.com.ledscolatina.backend.util.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(itemService.index());
    }

    @PostMapping
    public ResponseEntity<?> create(@DTO(ItemDTO.class) Item item) {
        return ResponseEntity.ok(itemService.create(item));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.show(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @DTO(ItemDTO.class) Item item) {
        item.setId(id);
        return ResponseEntity.ok(itemService.update(item));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
