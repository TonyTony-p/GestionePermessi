package it.permessi.rest.permessi.controller;

import it.permessi.rest.permessi.dto.PageResponse;
import it.permessi.rest.permessi.dto.IdRequest;
import it.permessi.rest.permessi.dto.UtenteFormDto;
import it.permessi.rest.permessi.dto.UtenteDto;
import it.permessi.rest.permessi.service.UtenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** API REST per Utenti. */
@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired private UtenteService service;

    /** Crea utente. */
    @PostMapping
    @PreAuthorize("hasAuthority('UTENTE_CREATE')")
    public UtenteDto create(@Valid @RequestBody UtenteFormDto form) {
        return service.create(form);
    }

    /** Aggiorna utente (id nel body). */
    @PutMapping
    @PreAuthorize("hasAuthority('UTENTE_UPDATE')")
    public UtenteDto update(@Valid @RequestBody UtenteFormDto form) {
        return service.update(form);
    }

    /** Elimina utente (id nel body). */
    @DeleteMapping
    @PreAuthorize("hasAuthority('UTENTE_DELETE')")
    public void delete(@Valid @RequestBody IdRequest req) {
        service.delete(req.getId());
    }

    /** Dettaglio utente. */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('UTENTE_READ')")
    public UtenteDto get(@PathVariable Long id) {
        return service.getById(id);
    }

    /** Lista paginata. */
    @GetMapping
    @PreAuthorize("hasAuthority('UTENTE_READ')")
    public PageResponse<UtenteDto> list(Pageable pageable) {
        return service.list(pageable);
    }

    /** Lista completa (non paginata). */
    @GetMapping("/tutti")
    @PreAuthorize("hasAuthority('UTENTE_READ')")
    public List<UtenteDto> listAll() {
        return service.listAll();
    }
}
