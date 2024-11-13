package com.fotoflow.fotoflowApi.controller.usuarios;

import com.fotoflow.fotoflowApi.model.usuarios.cliente.ClienteDto;
import com.fotoflow.fotoflowApi.model.usuarios.cliente.ClienteModel;
import com.fotoflow.fotoflowApi.repository.usuarios.ClienteRepository;
import com.fotoflow.fotoflowApi.service.usuarios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private ClienteService service;

    //GET
    @GetMapping
    public List<ClienteModel> get() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity post(@RequestBody ClienteDto dto) {
        service.cadCliente(dto.nome(), dto.email(), dto.senha(), dto.tel(), dto.ende());
        return new ResponseEntity("Cliente cadastrado", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity del(@PathVariable Long id) {
        var cliOpt = repo.findById(id);
        if(cliOpt.isPresent()) {
            repo.deleteById(id);
            return new ResponseEntity("Cliente excluído", HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Long id, @RequestBody ClienteDto dto) {
        var cliOpt = repo.findById(id);

        if(cliOpt.isPresent()) {
            service.atualizarCliente(id.intValue(), dto.nome(), dto.email(), dto.senha(), dto.tel(), dto.ende());
            return new ResponseEntity("Cliente Atualizado", HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
