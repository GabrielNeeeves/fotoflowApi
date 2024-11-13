package com.fotoflow.fotoflowApi.model.foto;

import com.fotoflow.fotoflowApi.model.album.AlbumModel;
import jakarta.persistence.*;

@Entity
@Table(name = "fotos")
public class FotoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_foto;

    private String url;

    private String descricao;

    // Relacionamento com Album
    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private AlbumModel album;
}
