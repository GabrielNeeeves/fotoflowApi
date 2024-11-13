package com.fotoflow.fotoflowApi.repository.pagamento;

import com.fotoflow.fotoflowApi.model.pagamento.PagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<PagamentoModel, Long> {
}
