package com.fotoflow.fotoflowApi.model.foto;

import jakarta.persistence.*;

@Entity
@Table(name = "FOTOS")
public class FotoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_foto;

    private String url;

    private String descricao;

    private Integer album_id;

    public FotoModel(){}

    public Long getId_foto() {
        return id_foto;
    }

    public void setId_foto(Long id_foto) {
        this.id_foto = id_foto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Integer album_id) {
        this.album_id = album_id;
    }
}
