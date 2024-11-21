package com.fotoflow.fotoflowApi.model.usuarios.cliente;

import jakarta.persistence.*;

@Entity
@Table(name = "v_CLIENTES")
public class ClienteView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_USUARIO;

    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String endereco;


    public Long getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(Long ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
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
