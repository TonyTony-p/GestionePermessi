package it.permessi.rest.permessi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/** Form create/update per Cane (id nullo = create). */
public class CaneFormDto {
    private Long id;

    @NotBlank(message = "Il nome del cane è obbligatorio")
    private String nome;

    private LocalDate dataNascita;
    private Double peso;
    private Boolean pedigree;

    /** Proprietario: obbligatorio in create (solo id richiesto). */
    @NotNull(message = "Il proprietario è obbligatorio e deve avere almeno l'id")
    private UtenteDto proprietario;

    /** Razza: obbligatoria. */
    @NotNull(message = "La razza è obbligatoria e deve avere almeno l'id")
    private RazzaDto razza;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDate getDataNascita() { return dataNascita; }
    public void setDataNascita(LocalDate dataNascita) { this.dataNascita = dataNascita; }
    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }
    public Boolean getPedigree() { return pedigree; }
    public void setPedigree(Boolean pedigree) { this.pedigree = pedigree; }
    public UtenteDto getProprietario() { return proprietario; }
    public void setProprietario(UtenteDto proprietario) { this.proprietario = proprietario; }
    public RazzaDto getRazza() { return razza; }
    public void setRazza(RazzaDto razza) { this.razza = razza; }
}