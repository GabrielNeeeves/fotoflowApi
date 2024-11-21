package com.fotoflow.fotoflowApi.repository.usuarios;

import com.fotoflow.fotoflowApi.model.usuarios.cliente.ClienteView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteViewRepository extends JpaRepository<ClienteView, Long> {
}
