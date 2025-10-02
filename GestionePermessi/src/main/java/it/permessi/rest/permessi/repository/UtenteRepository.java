package it.permessi.rest.permessi.repository;

import it.permessi.rest.permessi.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/** Repository CRUD per Utente + finder per email (login). */
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByEmail(String email);
}
