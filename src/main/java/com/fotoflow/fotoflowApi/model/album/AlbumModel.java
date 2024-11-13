package com.fotoflow.fotoflowApi.model.album;

import com.fotoflow.fotoflowApi.model.foto.FotoModel;
import com.fotoflow.fotoflowApi.model.usuarios.fotografo.FotografoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "album")
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
    private TipoPacote tipoPacote;

    //número de likes no álbum
    private Long curtidas;

    // Relacionamento com Foto
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FotoModel> fotos;

    // Relacionamento com Fotografo
    @ManyToOne
    @JoinColumn(name = "fotografo_id", nullable = false)
    private FotografoModel fotografo;
}
