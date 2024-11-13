package com.fotoflow.fotoflowApi.controller.fotos;

import com.fotoflow.fotoflowApi.model.foto.FotoDto;
import com.fotoflow.fotoflowApi.model.foto.FotoModel;
import com.fotoflow.fotoflowApi.repository.foto.FotoRepository;
import com.fotoflow.fotoflowApi.service.foto.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fotos")
public class FotoController {

    @Autowired
    private FotoRepository repo;

    @Autowired
    private FotoService service;

    //GET
    @GetMapping
    public List<FotoModel> get() {
        return repo.findAll();
    }

    //POST
    @PostMapping("/post")
    public ResponseEntity post(@RequestBody FotoDto dto) {
        service.cadFoto(dto.url(), dto.descricao(), dto.album_id());
        return new ResponseEntity("Foto criada", HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity del(@PathVariable Long id) {
        var fotoOpt = repo.findById(id);
        if(fotoOpt.isPresent()) {
            repo.deleteById(id);
            return new ResponseEntity("Foto excluida", HttpStatus.OK);
        }
        return new ResponseEntity("Foto não encontrada", HttpStatus.NOT_FOUND);
    }

    //PUT
    @PutMapping("/put/{id}")
    public ResponseEntity put(@PathVariable Long id, @RequestBody FotoDto dto) {
        var fotoOpt = repo.findById(id);
        if(fotoOpt.isPresent()) {
            service.updateFoto(id.intValue(), dto.url(), dto.descricao(), dto.album_id());
            return new ResponseEntity("Foto atualizada", HttpStatus.OK);
        }
        return new ResponseEntity("Foto não encontrada", HttpStatus.NOT_FOUND);
    }

}
