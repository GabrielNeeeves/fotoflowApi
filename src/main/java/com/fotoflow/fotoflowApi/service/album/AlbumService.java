package com.fotoflow.fotoflowApi.service.album;

import com.fotoflow.fotoflowApi.repository.album.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository repo;

    //CADASTRAR ALBUM
    public void cadAlbum(String nome, String tipo_fotografia, String tipo_pacote, Integer fotografo_id) {
        repo.cadAlbum(nome, tipo_fotografia, tipo_pacote, fotografo_id);
    }

    //ATULAIZAR ALBUM
    public void atualizarAlbum(Integer album_id, String nv_nome, String nv_tipo_fotografia, String nv_tipo_pacote, Integer nv_curtidas, Integer nv_fotografo_id) {
        repo.atualizarAlbum(album_id, nv_nome, nv_tipo_fotografia, nv_tipo_pacote, nv_curtidas, nv_fotografo_id);
    }

}
