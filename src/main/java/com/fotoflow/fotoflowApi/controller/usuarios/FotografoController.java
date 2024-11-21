package com.fotoflow.fotoflowApi.controller.usuarios;

import com.fotoflow.fotoflowApi.model.usuarios.fotografo.FotografoDto;
import com.fotoflow.fotoflowApi.model.usuarios.fotografo.FotografoModel;
import com.fotoflow.fotoflowApi.model.usuarios.fotografo.FotografoView;
import com.fotoflow.fotoflowApi.repository.usuarios.FotografoRepository;
import com.fotoflow.fotoflowApi.repository.usuarios.FotografoViewRepository;
import com.fotoflow.fotoflowApi.service.usuarios.FotografoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fotografos")
public class FotografoController {

    @Autowired
    private FotografoRepository repo;

    @Autowired
    private FotografoViewRepository fotografoViewRepository;

    @Autowired
    FotografoService service;

    //GET
    @GetMapping
    public List<FotografoView> get() {
        return fotografoViewRepository.findAll();
    }

    //POST
    @PostMapping("/post")
    public void cadastrarFotografo(@RequestBody FotografoDto fotografoDto) {
        service.cadastrarFotografo(
                fotografoDto.nome(),
                fotografoDto.email(),
                fotografoDto.senha(),
                fotografoDto.tel(),
                fotografoDto.ende(),
                fotografoDto.esp(),
                fotografoDto.cert()
        );
    }

    //DELETE BY ID
    @DeleteMapping("delete/{id}")
    public ResponseEntity delFotografo(@PathVariable Long id) {
        Optional<FotografoModel> fotOpt = repo.findById(id);

        if(fotOpt.isPresent()) {
            var fotDel = fotOpt.get();
            repo.deleteById(fotDel.getId());
            return new ResponseEntity("Fotográfo excluído", HttpStatus.OK);
        }
        return new ResponseEntity("Fotográfo não encontrado", HttpStatus.NOT_FOUND);
    }

    //PUT BY ID
    @PutMapping("put/{id}")
    public ResponseEntity putFotografo(@PathVariable Long id, @RequestBody FotografoDto dto) {
        Optional<FotografoModel> fotOpt = repo.findById(id);

        if(fotOpt.isPresent()) {
            service.atualizarFotografo(id.intValue(), dto.nome(), dto.email(), dto.senha(), dto.tel(), dto.ende(), dto.esp(), dto.cert());
            return new ResponseEntity("Fotográfo atualizado", HttpStatus.OK);
        }
        return new ResponseEntity("Fotográfo não encontrado", HttpStatus.NOT_FOUND);
    }
}
