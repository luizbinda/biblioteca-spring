package br.com.ledscolatina.backend.repository;

import br.com.ledscolatina.backend.model.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TituloRepository extends JpaRepository<Titulo, Long> {
}
