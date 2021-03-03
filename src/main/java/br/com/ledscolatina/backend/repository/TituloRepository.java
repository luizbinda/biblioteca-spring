package br.com.ledscolatina.backend.repository;

import br.com.ledscolatina.backend.model.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TituloRepository extends JpaRepository<Titulo, Long> {
    List<Titulo> findByAtoresId(Long id);

    List<Titulo> findByCategoria(String categoria);
}
