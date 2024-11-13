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

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    private StatusPagamento status;

    private LocalDate data_criacao;

    private LocalDate data_vencimento;

    private Long usuario_id;

    private Long fotografo_id;
}
