package com.fotoflow.fotoflowApi.repository.usuarios;

import com.fotoflow.fotoflowApi.model.usuarios.cliente.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
}
