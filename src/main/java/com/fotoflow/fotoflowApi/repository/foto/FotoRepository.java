package com.fotoflow.fotoflowApi.repository.foto;

import com.fotoflow.fotoflowApi.model.foto.FotoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface FotoRepository extends JpaRepository<FotoModel, Long> {

    //CADASTRAR FOTO
    @Procedure(procedureName = "SP_cadfoto")
    void cadFoto(String url, String descricao, Integer album_id);

    //ATUALIZAR FOTO
    @Procedure(procedureName = "SP_atualizarfoto")
    void updateFoto(Integer foto_id, String nv_url, String nv_descricao, Integer nv_album_id);

}
