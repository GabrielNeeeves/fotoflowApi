package com.fotoflow.fotoflowApi.model.pagamento;

import com.fotoflow.fotoflowApi.model.usuarios.UsuarioModel;
import com.fotoflow.fotoflowApi.model.usuarios.fotografo.FotografoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pagamentos")
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pagamento;

    private Double valor;

    private TipoPagamento tipoPagamento;

    private StatusPagamento statusPagamento;

    private LocalDate dataCriacao;

    private LocalDate dataVencimento;

    // Relacionamento com Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel usuario;

    // Relacionamento com Fotografo
    @ManyToOne
    @JoinColumn(name = "fotografo_id", nullable = false)
    private FotografoModel fotografo;
}
