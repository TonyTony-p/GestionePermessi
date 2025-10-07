package it.permessi.rest.permessi.dto;

import jakarta.validation.constraints.NotBlank;

/** Form create/update per Razza (id nullo = create). */
public class RazzaFormDto {
    private Long id;

    @NotBlank(message = "Il nome della razza è obbligatorio")
    private String nome;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}