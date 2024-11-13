package com.fotoflow.fotoflowApi.model.usuarios.fotografo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "FOTOGRAFOS")
@Getter
@Setter
public class FotografoModel {

    @Id
    private Long id_fotografo;

    private String especialidade;

    @Column(nullable = true)
    private String certificacoes;

    public FotografoModel(){}

    public FotografoModel(String especialidade, String certificacoes) {
        this.especialidade = especialidade;
        this.certificacoes = certificacoes;
    }

    public Long getId() {
        return id_fotografo;
    }

    public void setId(Long id) {
        this.id_fotografo = id;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCertificacoes() {
        return certificacoes;
    }

    public void setCertificacoes(String certificacoes) {
        this.certificacoes = certificacoes;
    }
}
