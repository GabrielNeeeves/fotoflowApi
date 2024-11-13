package com.fotoflow.fotoflowApi.controller.usuarios.fotografo;

import com.fotoflow.fotoflowApi.model.usuarios.fotografo.FotografoModel;
import com.fotoflow.fotoflowApi.repository.usuarios.FotografoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fotografos")
public class FotografoController {

    @Autowired
    private FotografoRepository repo;

    //GET
    @GetMapping
    public List<FotografoModel> get() {
        return repo.findAll();
    }

    //POST
    @PostMapping
    public void post(@RequestBody FotografoModel fotografo) {
            var novoFotografo = new FotografoModel(fotografo.getUsuario(), fotografo.getEspecialidade(), fotografo.getCertificacoes());
            repo.save(novoFotografo);
//            return new ResponseEntity(novoFotografo, HttpStatus.CREATED);
//            return new ResponseEntity("Falha ao criar fotógrafo", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
