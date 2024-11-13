package com.fotoflow.fotoflowApi.controller.pagamento;

import com.fotoflow.fotoflowApi.model.pagamento.PagamentoDto;
import com.fotoflow.fotoflowApi.model.pagamento.PagamentoModel;
import com.fotoflow.fotoflowApi.repository.pagamento.PagamentoRepository;
import com.fotoflow.fotoflowApi.service.pagamento.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository repo;

//    @Autowired
//    private PagamentoService service;

    //GET
    @GetMapping
    public List<PagamentoModel> get() {
        return repo.findAll();
    }

    //POST
    @PostMapping("/post")
    public ResponseEntity post(@RequestBody PagamentoDto dto) {
        var nvPag = new PagamentoModel(dto);
        repo.save(nvPag);
        return new ResponseEntity("Pagamento criado", HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        var pagOpt = repo.findById(id);

        if(pagOpt.isPresent()) {
            PagamentoModel p = pagOpt.get();
            p.setStatus("CANCELADO");
            repo.save(p);
            return new ResponseEntity("Status do pagamento mudado para 'CANCELADO'", HttpStatus.OK);
        }
        return new ResponseEntity("Pagamento não encontrado", HttpStatus.NOT_FOUND);
    }

    //PUT
    @PutMapping("/put/{id}")
    public ResponseEntity put(@PathVariable Long id, @RequestBody PagamentoDto dto) {
        var pagOpt = repo.findById(id);

        if(pagOpt.isPresent()) {
            var p = pagOpt.get();
            p.setValor(dto.valor());
            p.setTipoPagamento(dto.tipo_pagamento());
            p.setStatus(dto.status());
            p.setData_criacao(dto.data_criacao());
            p.setData_vencimento(dto.data_vencimento());
            p.setCliente_id(dto.cliente_id());
            p.setFotografo_id(dto.fotografo_id());

            repo.save(p);

            return new ResponseEntity("Pagamento atualizado", HttpStatus.OK);
        }
        return new ResponseEntity("Pagamento não encontrado", HttpStatus.NOT_FOUND);
    }
}
