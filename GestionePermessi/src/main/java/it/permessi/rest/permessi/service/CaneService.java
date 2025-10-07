package it.permessi.rest.permessi.service;

import it.permessi.rest.permessi.dto.PageResponse;
import it.permessi.rest.permessi.dto.CaneDto;
import it.permessi.rest.permessi.dto.CaneFormDto;
import it.permessi.rest.permessi.entity.Cane;
import it.permessi.rest.permessi.entity.Razza;
import it.permessi.rest.permessi.entity.Utente;
import it.permessi.rest.permessi.mapper.DtoMapper;
import it.permessi.rest.permessi.repository.CaneRepository;
import it.permessi.rest.permessi.repository.RazzaRepository;
import it.permessi.rest.permessi.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

/** Logica di business per Cani (CRUD). */
@Service
public class CaneService {

    @Autowired private CaneRepository repo;
    @Autowired private UtenteRepository utenteRepo;
    @Autowired private RazzaRepository razzaRepo;

    /** Crea cane con proprietario e razza. */
    public CaneDto create(CaneFormDto form) {
        Cane c = new Cane();
        apply(c, form);
        return DtoMapper.toCaneDtoLight(repo.save(c));
    }

    /** Aggiorna cane. */
    public CaneDto update(CaneFormDto form) {
        if (form.getId() == null) throw new RuntimeException("Id cane obbligatorio per update");
        Cane c = repo.findById(form.getId())
                .orElseThrow(() -> new RuntimeException("Cane non trovato"));
        apply(c, form);
        return DtoMapper.toCaneDtoLight(repo.save(c));
    }

    /** Elimina cane. */
    public void delete(Long id) { repo.deleteById(id); }

    /** Dettaglio cane. */
    public CaneDto getById(Long id) {
        return repo.findById(id)
                .map(DtoMapper::toCaneDtoLight)
                .orElseThrow(() -> new RuntimeException("Cane non trovato"));
    }

    /** Lista paginata cani. */
    public PageResponse<CaneDto> list(Pageable pageable) {
        Page<Cane> page = repo.findAll(pageable);
        return PageResponse.from(page.map(DtoMapper::toCaneDtoLight));
    }

    /** Lista completa non paginata cani. */
    public List<CaneDto> listAll() {
        return repo.findAll().stream()
                .map(DtoMapper::toCaneDtoLight)
                .collect(Collectors.toList());
    }

    /** Lista dei cani dell'utente corrente (ricavato dal JWT). */
    public List<CaneDto> listMine() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente u = utenteRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utente corrente non trovato"));
        return repo.findByProprietario_Id(u.getId()).stream()
                .map(DtoMapper::toCaneDtoLight)
                .collect(Collectors.toList());
    }

    /** Copia campi e risolve relazioni. */
    private void apply(Cane c, CaneFormDto form) {
        c.setNome(form.getNome());
        c.setDataNascita(form.getDataNascita());
        c.setPeso(form.getPeso());
        c.setPedigree(form.getPedigree());

        Utente u = utenteRepo.findById(form.getProprietario().getId())
                .orElseThrow(() -> new RuntimeException("Utente proprietario non trovato"));
        c.setProprietario(u);

        Razza r = razzaRepo.findById(form.getRazza().getId())
                .orElseThrow(() -> new RuntimeException("Razza non trovata"));
        c.setRazza(r);
    }
}