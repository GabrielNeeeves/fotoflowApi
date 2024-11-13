package com.fotoflow.fotoflowApi.model.pagamento;

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

    private String tipoPagamento;

    private String status;

    private LocalDate data_criacao;

    private LocalDate data_vencimento;

    private Integer cliente_id;

    private Integer fotografo_id;

    //public PagamentoModel(){}

    public PagamentoModel(PagamentoDto dto) {
        valor = dto.valor();
        tipoPagamento = dto.tipo_pagamento();
        status = dto.status();
        data_criacao = dto.data_criacao();
        data_vencimento = dto.data_vencimento();
        cliente_id = dto.cliente_id();
        fotografo_id = dto.fotografo_id();

    }

    public Long getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(Long id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDate data_criacao) {
        this.data_criacao = data_criacao;
    }

    public LocalDate getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(LocalDate data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Integer getFotografo_id() {
        return fotografo_id;
    }

    public void setFotografo_id(Integer fotografo_id) {
        this.fotografo_id = fotografo_id;
    }
}
