package com.fotoflow.fotoflowApi.repository.usuarios;

import com.fotoflow.fotoflowApi.model.usuarios.fotografo.FotografoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotografoRepository extends JpaRepository<FotografoModel, Long> {
}
