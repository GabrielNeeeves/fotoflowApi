package com.fotoflow.fotoflowApi.controller.album;

import com.fotoflow.fotoflowApi.model.album.AlbumDto;
import com.fotoflow.fotoflowApi.model.album.AlbumModel;
import com.fotoflow.fotoflowApi.repository.album.AlbumRepository;
import com.fotoflow.fotoflowApi.service.album.AlbumService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumRepository repo;

    @Autowired
    private AlbumService service;

    //GET
    @GetMapping
    public List<AlbumModel> get() {
        return repo.findAll();
    }

    //POST
    @PostMapping("/post")
    public ResponseEntity postAlbum(@RequestBody AlbumDto dto) {
        service.cadAlbum(dto.nome(), dto.tipo_fotografia(), dto.tipo_pacote(), dto.fotografo_id());
        return new ResponseEntity("Album cadastrado", HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping("delete/{id}")
    public ResponseEntity delAlbum(@PathVariable Long id) {
        Optional<AlbumModel> albumOptional = repo.findById(id);

        if(albumOptional.isPresent()) {
            repo.deleteById(id);
            return new ResponseEntity(albumOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity("Album não encontrado", HttpStatus.NOT_FOUND);
    }

    //PUT
    @PutMapping("put/{id}")
    public ResponseEntity updateAlbum(@PathVariable Long id, @RequestBody AlbumDto dto) {
        var albumOptional = repo.findById(id);

        if(albumOptional.isPresent()) {
            service.atualizarAlbum(id.intValue(),
                    dto.nome(),
                    dto.tipo_fotografia(),
                    dto.tipo_pacote(),
                    dto.curtidas(),
                    dto.fotografo_id());
            return new ResponseEntity("Album atualizado", HttpStatus.OK);
        }
        return new ResponseEntity("Album não encontrado", HttpStatus.NOT_FOUND);
    }

}
