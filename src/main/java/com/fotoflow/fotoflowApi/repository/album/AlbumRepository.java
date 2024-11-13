package com.fotoflow.fotoflowApi.repository.album;

import com.fotoflow.fotoflowApi.model.album.AlbumModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<AlbumModel, Long> {
}
