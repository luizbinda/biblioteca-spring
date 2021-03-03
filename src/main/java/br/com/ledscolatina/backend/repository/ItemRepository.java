package br.com.ledscolatina.backend.repository;

import br.com.ledscolatina.backend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByNumSerie(String numSerie);
}
