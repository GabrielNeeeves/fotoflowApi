package com.fotoflow.fotoflowApi.model.pagamento;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;

public record PagamentoDto(Double valor,
                           String tipo_pagamento,
                           String status,
                           LocalDate data_criacao,
                           LocalDate data_vencimento,
                           Integer cliente_id,
                           Integer fotografo_id) {
}
