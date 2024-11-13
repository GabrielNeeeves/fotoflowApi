package com.fotoflow.fotoflowApi.service.usuarios;

import com.fotoflow.fotoflowApi.repository.usuarios.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    //CADASTRAR CLIENTE
    public void cadCliente(String nome, String email, String senha, String tel, String ende) {
        repo.cadCliente(nome, email, senha, tel, ende);
    }

    //ATUALIZAR CLIENTE
    public void atualizarCliente(Integer cliente_id, String novo_nome, String novo_email, String novo_senha, String novo_tel, String novo_ende) {
        repo.atualizarCliente(cliente_id, novo_nome, novo_email, novo_senha, novo_tel, novo_ende);
    }

}
