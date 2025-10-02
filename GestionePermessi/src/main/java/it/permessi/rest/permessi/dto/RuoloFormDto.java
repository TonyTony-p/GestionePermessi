package it.permessi.rest.permessi.dto;

import jakarta.validation.constraints.NotBlank;

/** Form create/update per Ruolo (senza permessi). */
public class RuoloFormDto {
    private Long id;

    @NotBlank(message = "Il nome del ruolo è obbligatorio")
    private String nome;

    @NotBlank(message = "L'alias del ruolo è obbligatorio")
    private String alias;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
}
