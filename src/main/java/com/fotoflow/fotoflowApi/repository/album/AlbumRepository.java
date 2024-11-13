package com.fotoflow.fotoflowApi.repository.album;

import com.fotoflow.fotoflowApi.model.album.AlbumModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface AlbumRepository extends JpaRepository<AlbumModel, Long> {

    //CADASTRAR ALBUM
    @Procedure(procedureName = "sp_cadalbum")
    void cadAlbum(
            String nome,
            String tipo_fotografia,
            String tipo_pacote,
            Integer fotografo_id
    );

    //ATUALIZAR ALBUM
    @Procedure(procedureName = "sp_atualizaralbum")
    void atualizarAlbum(
            Integer album_id,
            String nv_nome,
            String nv_tipo_fotografia,
            String nv_tipo_pacote,
            Integer nv_curtidas,
            Integer nv_fotografo_id
    );

}
