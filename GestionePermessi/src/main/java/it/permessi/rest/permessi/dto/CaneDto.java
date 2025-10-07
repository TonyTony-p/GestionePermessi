package it.permessi.rest.permessi.dto;

import java.time.Instant;
import java.time.LocalDate;

/** DTO di lettura per Cane: include proprietario (light) e razza. */
public class CaneDto {
    private Long id;
    private String nome;
    private LocalDate dataNascita;
    private Double peso;
    private Boolean pedigree;
    private UtenteDto proprietario; // utente light
    private RazzaDto razza; // razza light
    private Instant createdAt;
    private Instant updatedAt;

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
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}