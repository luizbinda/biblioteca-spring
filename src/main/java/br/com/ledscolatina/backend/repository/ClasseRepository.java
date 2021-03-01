package br.com.ledscolatina.backend.repository;

import br.com.ledscolatina.backend.model.Ator;
import br.com.ledscolatina.backend.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
}
