package com.fotoflow.fotoflowApi.model.usuarios;

import com.fotoflow.fotoflowApi.model.usuarios.fotografo.FotografoModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USUARIOS")
//@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String nome;

    private String email;

    private String senha;

    private String telefone;

    private String endereco;

    public UsuarioModel(){}

    public UsuarioModel(UsuarioDto dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.senha = dto.senha();
        this.telefone = dto.tel();
        this.endereco = dto.endereco();
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
