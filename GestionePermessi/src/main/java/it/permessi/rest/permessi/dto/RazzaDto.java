package it.permessi.rest.permessi.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;



/** DTO di lettura per Razza. */
public class RazzaDto {
    private Long id;
    private String nome;
    private List<CaneDto> cani = new ArrayList<>();
    private Instant createdAt;
    private Instant updatedAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public List getCani() {return cani; }
    public void setCani(List <CaneDto> cani) {this.cani = cani;}
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}