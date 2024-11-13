package com.fotoflow.fotoflowApi.model.album;

import com.fotoflow.fotoflowApi.model.foto.FotoModel;
import com.fotoflow.fotoflowApi.model.usuarios.fotografo.FotografoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ALBUM")
@NoArgsConstructor
@AllArgsConstructor
public class AlbumModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_album;

    private String nome;

    //ex: natureza, casamento, etc
    private String tipoFotografia;

    //BRONZE, PRATA, OURO - para determinar o preço final
    @Enumerated(EnumType.STRING)
    private TipoPacote tipoPacote;

    private Integer curtidas;

    //public AlbumModel() {}

    public Long getId_album() {
        return id_album;
    }

    public void setId_album(Long id_album) {
        this.id_album = id_album;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoFotografia() {
        return tipoFotografia;
    }

    public void setTipoFotografia(String tipoFotografia) {
        this.tipoFotografia = tipoFotografia;
    }

    public TipoPacote getTipoPacote() {
        return tipoPacote;
    }

    public void setTipoPacote(TipoPacote tipoPacote) {
        this.tipoPacote = tipoPacote;
    }

    public Integer getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(Integer curtidas) {
        this.curtidas = curtidas;
    }
}
