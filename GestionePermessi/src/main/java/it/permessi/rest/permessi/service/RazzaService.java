package it.permessi.rest.permessi.service;

import it.permessi.rest.permessi.dto.PageResponse;
import it.permessi.rest.permessi.dto.RazzaDto;
import it.permessi.rest.permessi.dto.RazzaFormDto;
import it.permessi.rest.permessi.entity.Razza;
import it.permessi.rest.permessi.mapper.DtoMapper;
import it.permessi.rest.permessi.repository.RazzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/** Logica di business per Razze (CRUD). */
@Service
public class RazzaService {

    @Autowired private RazzaRepository repo;

    /** Crea razza. */
    public RazzaDto create(RazzaFormDto form) {
        Razza r = new Razza();
        r.setNome(form.getNome());
        return DtoMapper.toRazzaDtoLight(repo.save(r));
    }

    /** Aggiorna razza. */
    public RazzaDto update(RazzaFormDto form) {
        if (form.getId() == null) throw new RuntimeException("Id razza obbligatorio per update");
        Razza r = repo.findById(form.getId())
                .orElseThrow(() -> new RuntimeException("Razza non trovata"));
        r.setNome(form.getNome());
        return DtoMapper.toRazzaDtoLight(repo.save(r));
    }
    
    

    /** Elimina razza. */
    public void delete(Long id) { repo.deleteById(id); }

    /** Dettaglio razza. */
    public RazzaDto getById(Long id) {
        return repo.findById(id)
                .map(DtoMapper::toRazzaDtoLight)
                .orElseThrow(() -> new RuntimeException("Razza non trovata"));
    }

    /** Lista paginata razze. */
    public PageResponse<RazzaDto> list(Pageable pageable) {
        Page<Razza> page = repo.findAll(pageable);
        return PageResponse.from(page.map(DtoMapper::toRazzaDtoLight));
    }
    
    public RazzaDto getCaniConRazzaById(Long id) {
        Razza r = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Razza non trovata"));
        
        return DtoMapper.toRazzaDtoWhitCane(r);
    }

    /** Lista completa non paginata razze. */
    public List<RazzaDto> listAll() {
        return repo.findAll().stream()
                .map(DtoMapper::toRazzaDtoLight)
                .collect(Collectors.toList());
    }
    
}