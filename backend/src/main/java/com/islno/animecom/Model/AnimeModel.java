package com.islno.animecom.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Map;

@Entity
public class AnimeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("title")
    private String titulo;

    @Column(columnDefinition = "TEXT")
    @JsonProperty("synopsis")
    private String sinopse;

    @JsonProperty("episodes")
    private Integer episodios;

    @JsonProperty("score")
    private Double nota;

    private String imagemUrl;
    private String trailerUrl;


    public AnimeModel() {}

    @JsonProperty("images")
    private void unpackNameFromNestedObject(Map<String, Object> images) {
        try {
            Map<String, Object> jpg = (Map<String, Object>) images.get("jpg");
            this.imagemUrl = (String) jpg.get("image_url");
        } catch (Exception e) {
            this.imagemUrl = null;
        }
    }

    @JsonProperty("trailer")
    private void unpackTrailerFromNestedObject(Map<String, Object> trailer) {
        try {
            String embed = (String) trailer.get("embed_url");
            String url = (String) trailer.get("url");
            if (embed != null) this.trailerUrl = embed;
            else if (url != null) this.trailerUrl = url;
            else this.trailerUrl = null;
        } catch (Exception e) {
            this.trailerUrl = null;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getSinopse() { return sinopse; }
    public void setSinopse(String sinopse) { this.sinopse = sinopse; }
    public Integer getEpisodios() { return episodios; }
    public void setEpisodios(Integer episodios) { this.episodios = episodios; }
    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }
    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }
    public String getTrailerUrl() { return trailerUrl; }
    public void setTrailerUrl(String trailerUrl) { this.trailerUrl = trailerUrl; }
}