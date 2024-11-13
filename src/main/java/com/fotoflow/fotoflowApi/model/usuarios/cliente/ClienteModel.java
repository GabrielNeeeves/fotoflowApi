package com.fotoflow.fotoflowApi.model.usuarios.cliente;

import com.fotoflow.fotoflowApi.model.usuarios.UsuarioModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CLIENTES")
@Getter
@Setter
public class ClienteModel{

    @Id
    private Long id_cliente;

    public Long getId_cliente() {
        return id_cliente;
    }

    public ClienteModel(){}

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }
}
