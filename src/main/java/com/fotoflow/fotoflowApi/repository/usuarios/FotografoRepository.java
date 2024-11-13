package com.fotoflow.fotoflowApi.repository.usuarios;

import com.fotoflow.fotoflowApi.model.usuarios.fotografo.FotografoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface FotografoRepository extends JpaRepository<FotografoModel, Long> {

    @Procedure(procedureName = "sp_cadfotografo")
    void cadFotografo(String nome, String email, String senha, String tel, String ende, String esp, String cert);

    @Procedure(procedureName = "sp_atualizarfotografo")
    void atualizarFotografo(
            Integer fotografo_id,
            String novo_nome,
            String novo_email,
            String novo_senha,
            String novo_tel,
            String novo_ende,
            String novo_esp,
            String novo_cert
    );
}
