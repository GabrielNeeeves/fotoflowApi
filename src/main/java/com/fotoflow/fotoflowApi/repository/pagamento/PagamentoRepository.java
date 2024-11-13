package com.fotoflow.fotoflowApi.repository.pagamento;

import com.fotoflow.fotoflowApi.model.pagamento.PagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.time.LocalDate;

public interface PagamentoRepository extends JpaRepository<PagamentoModel, Long> {

    //CADASTRAR PAGAMENTO
    @Procedure(procedureName = "sp_cadpagamento")
    void cadPagamento(
            Double sp_valor,
            String sp_tipo_pagamento,
            String sp_status,
            LocalDate data_criacao,
            LocalDate data_vencimento,
            Integer sp_cliente_id,
            Integer sp_fotografo_id
    );

    //ATUALIZAR PAGAMENTO
    @Procedure(procedureName = "sp_atualizarpagamento")
    void updPagamento(
            Integer pagamento_id,
            Double sp_valor,
            String sp_tipo_pagamento,
            String sp_status,
            LocalDate data_criacao,
            LocalDate data_vencimento,
            Integer sp_cliente_id,
            Integer sp_fotografo_id
    );
}
