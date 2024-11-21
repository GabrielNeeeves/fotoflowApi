package com.fotoflow.fotoflowApi.repository.usuarios;

import com.fotoflow.fotoflowApi.model.usuarios.fotografo.FotografoView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotografoViewRepository extends JpaRepository<FotografoView, Long> {
}
