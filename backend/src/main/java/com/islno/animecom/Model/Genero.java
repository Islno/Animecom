package com.islno.animecom.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Genero {

    @Id
    @JsonProperty("mal_id")
    private Long id;

    @JsonProperty("name")
    private String nome;

    public Genero() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}