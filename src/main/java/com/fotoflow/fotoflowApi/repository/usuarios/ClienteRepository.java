package com.fotoflow.fotoflowApi.repository.usuarios;

import com.fotoflow.fotoflowApi.model.usuarios.cliente.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    @Procedure(procedureName = "SP_cadCliente")
    void cadCliente(
            String nome,
            String email,
            String senha,
            String tel,
            String ende);

    @Procedure(procedureName = "SP_atualizarCliente")
    void atualizarCliente(
            Integer cliente_id,
            String novo_nome,
            String novo_email,
            String novo_senha,
            String novo_tel,
            String novo_ende
    );
}
