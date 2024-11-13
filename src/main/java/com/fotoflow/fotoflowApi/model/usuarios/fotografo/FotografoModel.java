package com.fotoflow.fotoflowApi.model.usuarios.fotografo;

import com.fotoflow.fotoflowApi.model.album.AlbumModel;
import com.fotoflow.fotoflowApi.model.usuarios.UsuarioModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "fotografos")
@Getter
@Setter
public class FotografoModel {

    @Id
    private Long id_fotografo;

    @OneToOne
    @JoinColumn(name = "id_fotografo", referencedColumnName = "usuario_id")
    private UsuarioModel usuario;

    private String especialidade;

    @Column(nullable = true)
    private String certificacoes;

    // Relacionamento com Album
    @Column(nullable = true)
    @OneToMany(mappedBy = "fotografo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlbumModel> albuns;

    public FotografoModel(UsuarioModel usuario, String especialidade, String certificacoes) {
        this.usuario = usuario;
        this.especialidade = especialidade;
        this.certificacoes = certificacoes;
    }

//    public FotografoModel(UsuarioModel usuario, String especialidade) {
//        this.usuario = usuario;
//        this.especialidade = especialidade;
//    }

    public Long getId() {
        return id_fotografo;
    }

    public void setId(Long id) {
        this.id_fotografo = id;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
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

    public List<AlbumModel> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<AlbumModel> albuns) {
        this.albuns = albuns;
    }
}
