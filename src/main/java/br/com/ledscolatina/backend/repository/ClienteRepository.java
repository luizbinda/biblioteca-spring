package br.com.ledscolatina.backend.repository;

import br.com.ledscolatina.backend.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
