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
}
