package com.fotoflow.fotoflowApi.model.usuarios;

public record UsuarioDto(String nome, String email, String senha, String tel, String endereco, TipoUsuario tipoUsuario) {
}
