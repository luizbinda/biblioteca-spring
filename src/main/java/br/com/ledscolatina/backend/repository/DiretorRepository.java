package br.com.ledscolatina.backend.repository;

import br.com.ledscolatina.backend.model.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiretorRepository extends JpaRepository<Diretor, Long> {
}
