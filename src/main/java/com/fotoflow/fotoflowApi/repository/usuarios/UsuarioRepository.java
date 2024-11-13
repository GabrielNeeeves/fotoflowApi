package com.fotoflow.fotoflowApi.repository.usuarios;

import com.fotoflow.fotoflowApi.model.usuarios.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
}
