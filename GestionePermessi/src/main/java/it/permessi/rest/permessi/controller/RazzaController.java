package it.permessi.rest.permessi.controller;

import it.permessi.rest.permessi.dto.PageResponse;
import it.permessi.rest.permessi.dto.RazzaDto;
import it.permessi.rest.permessi.dto.RazzaFormDto;
import it.permessi.rest.permessi.service.RazzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/razze")
public class RazzaController {

    @Autowired private RazzaService service;

    @PreAuthorize("hasAuthority('RAZZA_CREATE')")
    @PostMapping
    public RazzaDto create(@RequestBody @Valid RazzaFormDto form) {
        return service.create(form);
    }

    @PreAuthorize("hasAuthority('RAZZA_UPDATE')")
    @PutMapping
    public RazzaDto update(@RequestBody @Valid RazzaFormDto form) {
        return service.update(form);
    }

    @PreAuthorize("hasAuthority('RAZZA_DELETE')")
    @DeleteMapping
    public void delete(@RequestBody @jakarta.validation.Valid it.permessi.rest.permessi.dto.IdRequest req) { service.delete(req.getId()); }

    @PreAuthorize("hasAuthority('RAZZA_READ')")
    @GetMapping("/{id}")
    public RazzaDto getById(@PathVariable Long id) { return service.getById(id); }
    
  //getRazzaById
    //inserire la lista di cani in razze
    @GetMapping("/{id}/listaCani")
    @PreAuthorize("hasAuthority('RAZZA_READ')")
    public RazzaDto getCaniConRazzaById(@PathVariable Long id) { return service.getCaniConRazzaById(id); }
 

    @GetMapping
    @PreAuthorize("hasAuthority('RAZZA_READ')")
    public PageResponse<RazzaDto> list(Pageable pageable) { return service.list(pageable); }

    @PreAuthorize("hasAuthority('RAZZA_READ')")
    @GetMapping("/all")
    public List<RazzaDto> listAll() { return service.listAll(); }
}