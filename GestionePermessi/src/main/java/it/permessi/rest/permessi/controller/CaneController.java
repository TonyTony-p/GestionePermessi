package it.permessi.rest.permessi.controller;

import it.permessi.rest.permessi.dto.PageResponse;
import it.permessi.rest.permessi.dto.CaneDto;
import it.permessi.rest.permessi.dto.CaneFormDto;
import it.permessi.rest.permessi.service.CaneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cani")
public class CaneController {

    @Autowired private CaneService service;

    @PreAuthorize("hasAuthority('CANE_CREATE')")
    @PostMapping
    public CaneDto create(@RequestBody @Valid CaneFormDto form) { return service.create(form); }

    @PreAuthorize("hasAuthority('CANE_UPDATE')")
    @PutMapping
    public CaneDto update(@RequestBody @Valid CaneFormDto form) { return service.update(form); }

    @PreAuthorize("hasAuthority('CANE_DELETE')")
    @DeleteMapping
    public void delete(@RequestBody @jakarta.validation.Valid it.permessi.rest.permessi.dto.IdRequest req) { service.delete(req.getId()); }

    @PreAuthorize("hasAuthority('CANE_READ')")
    @GetMapping("/{id}")
    public CaneDto getById(@PathVariable Long id) { return service.getById(id); }

    @PreAuthorize("hasAuthority('CANE_READ')")
    @GetMapping
    public PageResponse<CaneDto> list(Pageable pageable) { return service.list(pageable); }

    @PreAuthorize("hasAuthority('CANE_READ')")
    @GetMapping("/all")
    public List<CaneDto> listAll() { return service.listAll(); }

    /** Cani dell'utente corrente (id dal JWT). */
    @PreAuthorize("hasAuthority('CANE_READ_MIEI')")
    @GetMapping("/miei")
    public List<CaneDto> listMine() { return service.listMine(); }

}