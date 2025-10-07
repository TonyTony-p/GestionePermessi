package it.permessi.rest.permessi.repository;

import it.permessi.rest.permessi.entity.Cane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** Repository CRUD per Cane. */
public interface CaneRepository extends JpaRepository<Cane, Long> {
    List<Cane> findByProprietario_Id(Long utenteId);
    List<Cane> findByRazza_Id(Long razzaId);
}