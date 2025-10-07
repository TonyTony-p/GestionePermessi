package it.permessi.rest.permessi.repository;

import it.permessi.rest.permessi.entity.Razza;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository CRUD per Razza. */
public interface RazzaRepository extends JpaRepository<Razza, Long> {}