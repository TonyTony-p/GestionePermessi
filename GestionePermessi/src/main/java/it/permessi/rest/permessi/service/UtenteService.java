package it.permessi.rest.permessi.service;

import it.permessi.rest.permessi.dto.PageResponse;
import it.permessi.rest.permessi.dto.UtenteFormDto;
import it.permessi.rest.permessi.dto.UtenteDto;
import it.permessi.rest.permessi.entity.Ruolo;
import it.permessi.rest.permessi.entity.Utente;
import it.permessi.rest.permessi.repository.RuoloRepository;
import it.permessi.rest.permessi.repository.UtenteRepository;
import it.permessi.rest.permessi.mapper.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/** Logica di business per Utenti. */
@Service
public class UtenteService {

    @Autowired private UtenteRepository repo;
    @Autowired private RuoloRepository ruoloRepo;
    @Autowired private PasswordEncoder encoder;

    /** Crea utente (password codificata). */
    public UtenteDto create(UtenteFormDto form) {
        Utente u = new Utente();
        apply(u, form, true);
        return DtoMapper.toUtenteDto(repo.save(u)); // Ruolo light dentro UtenteDto
    }

    /** Aggiorna utente. */
    public UtenteDto update(UtenteFormDto form) {
        if (form.getId() == null) throw new RuntimeException("Id utente obbligatorio per update");
        Utente u = repo.findById(form.getId())
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
        apply(u, form, false);
        return DtoMapper.toUtenteDto(repo.save(u)); // Ruolo light
    }

    /** Elimina utente. */
    public void delete(Long id) { repo.deleteById(id); }

    /** Dettaglio utente. (Ruolo light) */
    public UtenteDto getById(Long id) {
        return repo.findById(id)
                .map(DtoMapper::toUtenteDto)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
    }

    /** Lista paginata utenti (mapper light). */
    public PageResponse<UtenteDto> list(Pageable pageable) {
        return PageResponse.from(repo.findAll(pageable).map(DtoMapper::toUtenteDto));
    }

    /** Lista completa non paginata (mapper light). */
    public List<UtenteDto> listAll() {
        return repo.findAll().stream()
                .map(DtoMapper::toUtenteDto)
                .collect(Collectors.toList());
    }

    /** Copia campi dal form, gestendo password e ruolo. */
    private void apply(Utente u, UtenteFormDto form, boolean encodePasswordAlways) {
        u.setNome(form.getNome());
        u.setCognome(form.getCognome());
        u.setCodiceFiscale(form.getCodiceFiscale());
        u.setEmail(form.getEmail());
        if (encodePasswordAlways || (form.getPassword() != null && !form.getPassword().isBlank())) {
            u.setPassword(encoder.encode(form.getPassword()));
        }
        u.setDataNascita(form.getDataNascita());
        u.setTelefono(form.getTelefono());
        u.setIndirizzo(form.getIndirizzo());

        Ruolo ruolo = ruoloRepo.findById(form.getRuolo().getId())
                .orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
        u.setRuolo(ruolo);
    }
}
