package com.fotoflow.fotoflowApi.repository.foto;

import com.fotoflow.fotoflowApi.model.foto.FotoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepository extends JpaRepository<FotoModel, Long> {
}
