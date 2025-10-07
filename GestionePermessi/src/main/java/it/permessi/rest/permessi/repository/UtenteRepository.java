package it.permessi.rest.permessi.repository;

import it.permessi.rest.permessi.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;

/** Repository CRUD per Utente + finder per email (login). */
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByEmail(String email);
    Optional<Utente> findByCodiceFiscale(String codiceFiscale);

    /** Carica utente con ruolo e permessi del ruolo già inizializzati. */
    @EntityGraph(attributePaths = {
        "ruolo",
        "ruolo.ruoloPermessi",
        "ruolo.ruoloPermessi.permesso"
    })
    Optional<Utente> findWithAuthoritiesByEmail(String email);
}
