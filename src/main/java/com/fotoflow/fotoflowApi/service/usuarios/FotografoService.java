package com.fotoflow.fotoflowApi.service.usuarios;

import com.fotoflow.fotoflowApi.repository.usuarios.FotografoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotografoService {

    @Autowired
    private FotografoRepository repo;

    //sp_cadFotografo
    public void cadastrarFotografo(String nome, String email, String senha, String tel, String ende, String esp, String cert) {
        repo.cadFotografo(nome, email, senha, tel, ende, esp, cert);
    }

    //sp_atualizarFotografo
    public void atualizarFotografo(Integer fotografo_id, String novo_nome, String novo_email, String novo_senha, String novo_tel, String novo_ende, String novo_esp, String novo_cert) {
        repo.atualizarFotografo(fotografo_id, novo_nome, novo_email, novo_senha, novo_tel, novo_ende, novo_esp, novo_cert);
    }

}
