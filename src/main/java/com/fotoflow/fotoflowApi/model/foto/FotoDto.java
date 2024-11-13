package com.fotoflow.fotoflowApi.model.foto;

public record FotoDto(
        String url,
        String descricao,
        Integer album_id
) {
}
