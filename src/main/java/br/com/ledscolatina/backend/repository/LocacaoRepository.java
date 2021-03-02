package br.com.ledscolatina.backend.repository;

import br.com.ledscolatina.backend.model.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
}
