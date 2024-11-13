package com.fotoflow.fotoflowApi.controller.pagamento;

import com.fotoflow.fotoflowApi.model.pagamento.PagamentoDto;
import com.fotoflow.fotoflowApi.model.pagamento.PagamentoModel;
import com.fotoflow.fotoflowApi.repository.pagamento.PagamentoRepository;
import com.fotoflow.fotoflowApi.service.pagamento.PagamentoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository repo;

    @Autowired
    private PagamentoService service;

    //GET
    @GetMapping
    public List<PagamentoModel> get() {
        return repo.findAll();
    }

    //POST
    @PostMapping("/post")
    public ResponseEntity post(@RequestBody PagamentoDto dto) {
        LocalDate criacao = LocalDate.parse(dto.data_criacao());
        LocalDate validade = LocalDate.parse(dto.data_vencimento());
        service.cadPagamento(
                dto.valor(),
                dto.tipo_pagamento(),
                dto.status(),
                criacao,
                validade,
                dto.cliente_id(),
                dto.fotografo_id()
        );
        return new ResponseEntity("Pagamento criado", HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        var pagOpt = repo.findById(id);

        if(pagOpt.isPresent()) {
            repo.deleteById(id);
            return new ResponseEntity("Status do pagamento mudado para 'CANCELADO'", HttpStatus.OK);
        }
        return new ResponseEntity("Pagamento não encontrado", HttpStatus.NOT_FOUND);
    }

    //PUT
    @PutMapping("/put/{id}")
    public ResponseEntity put(@PathVariable Long id, @RequestBody PagamentoDto dto) {
        var pagOpt = repo.findById(id);

        LocalDate criacao = LocalDate.parse(dto.data_criacao());
        LocalDate validade = LocalDate.parse(dto.data_vencimento());

        if(pagOpt.isPresent()) {
            service.updPagamento(
                    id.intValue(),
                    dto.valor(),
                    dto.tipo_pagamento(),
                    dto.status(),
                    criacao,
                    validade,
                    dto.cliente_id(),
                    dto.fotografo_id());
            return new ResponseEntity("Pagamento atualizado", HttpStatus.OK);
        }
        return new ResponseEntity("Pagamento não encontrado", HttpStatus.NOT_FOUND);
    }
}
