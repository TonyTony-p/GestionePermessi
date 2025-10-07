package it.permessi.rest.permessi.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;

/** Entità Cane: appartiene ad un Utente e ad una Razza. */
@Entity
@Table(name = "cani")
@EntityListeners(AuditingEntityListener.class)
public class Cane {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private LocalDate dataNascita;

    /** Peso del cane (kg). */
    private Double peso;

    /** Indica se il cane ha pedigree. */
    private Boolean pedigree;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente proprietario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "razza_id", nullable = false)
    private Razza razza;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
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
    public Utente getProprietario() { return proprietario; }
    public void setProprietario(Utente proprietario) { this.proprietario = proprietario; }
    public Razza getRazza() { return razza; }
    public void setRazza(Razza razza) { this.razza = razza; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}