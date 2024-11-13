package com.fotoflow.fotoflowApi.model.usuarios.cliente;

import com.fotoflow.fotoflowApi.model.usuarios.UsuarioModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class ClienteModel extends UsuarioModel {



}
