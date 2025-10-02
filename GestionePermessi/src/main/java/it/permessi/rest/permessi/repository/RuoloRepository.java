package it.permessi.rest.permessi.repository;

import it.permessi.rest.permessi.entity.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository CRUD per Ruolo. */
public interface RuoloRepository extends JpaRepository<Ruolo, Long> { }
