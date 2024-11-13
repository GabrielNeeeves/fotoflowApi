package com.fotoflow.fotoflowApi.service.foto;

import com.fotoflow.fotoflowApi.repository.foto.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoService {

    @Autowired
    private FotoRepository repo;

    //CADASTRAR FOTO
    public void cadFoto(String url, String descricao, Integer album_id) {
        repo.cadFoto(url, descricao, album_id);
    }

    //ATUALIZAR FOTO
    public void updateFoto(Integer foto_id, String nv_url, String nv_descricao, Integer nv_album_id) {
        repo.updateFoto(foto_id, nv_url, nv_descricao, nv_album_id);
    }

}
