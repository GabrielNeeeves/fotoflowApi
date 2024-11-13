package com.fotoflow.fotoflowApi.model.pagamento;

import java.time.LocalDate;

public record PagamentoDto(Double valor,
                           String tipo_pagamento,
                           String status,
                           String data_criacao,
                           String data_vencimento,
                           Integer cliente_id,
                           Integer fotografo_id) {
}
