package com.fotoflow.fotoflowApi.service.pagamento;

import com.fotoflow.fotoflowApi.repository.pagamento.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repo;

    //CADASTRAR PAGAMENTO
    public void cadPagamento(Double sp_valor,
                             String sp_tipo_pagamento,
                             String sp_status,
                             LocalDate data_criacao,
                             LocalDate data_vencimento,
                             Integer sp_cliente_id,
                             Integer sp_fotografo_id) {
        repo.cadPagamento(sp_valor, sp_tipo_pagamento, sp_status, data_criacao, data_vencimento, sp_cliente_id, sp_fotografo_id);
    }

    //ATUALIZAR PAGAMENTO
    public void updPagamento(Integer pagamento_id,
                             Double sp_valor,
                             String sp_tipo_pagamento,
                             String sp_status,
                             LocalDate data_criacao,
                             LocalDate data_vencimento,
                             Integer sp_cliente_id,
                             Integer sp_fotografo_id) {
        repo.updPagamento(pagamento_id, sp_valor, sp_tipo_pagamento, sp_status, data_criacao, data_vencimento, sp_cliente_id, sp_fotografo_id);
    }
}
