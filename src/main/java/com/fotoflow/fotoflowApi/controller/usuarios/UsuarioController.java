package com.fotoflow.fotoflowApi.controller.usuarios;

import com.fotoflow.fotoflowApi.model.usuarios.UsuarioDto;
import com.fotoflow.fotoflowApi.model.usuarios.UsuarioModel;
import com.fotoflow.fotoflowApi.repository.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repo;

    //GET
    @GetMapping
    public List<UsuarioModel> getUsers() {
        return repo.findAll();
    }

    //POST
    @PostMapping
    public ResponseEntity postUser(@RequestBody UsuarioDto dto) {
        try {
            var novoUsuario = new UsuarioModel(dto);
            repo.save(novoUsuario);
            return new ResponseEntity(novoUsuario, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity("Falha ao criar usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity delUser(@PathVariable Long id) {
        Optional<UsuarioModel> userOpt = repo.findById(id);
        if(userOpt.isPresent()) {
            repo.deleteById(id);
            return new ResponseEntity("USUARIO EXCLUIDO", HttpStatus.OK);
        }
        return new ResponseEntity("USUARIO NÃO ENCONTRADO", HttpStatus.NOT_FOUND);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity putUser(@PathVariable Long id, @RequestBody UsuarioModel u) {
        Optional<UsuarioModel> userOpt = repo.findById(id);
        if(userOpt.isPresent()) {
            var userUpt = new UsuarioModel();
            userUpt.setId_usuario(u.getId_usuario());
            userUpt.setNome(u.getNome());
            userUpt.setEmail(u.getEmail());
            userUpt.setSenha(u.getSenha());
            userUpt.setEndereco(u.getEndereco());
            userUpt.setTelefone(u.getTelefone());
            userUpt.setTipoUsuario(u.getTipoUsuario());

            repo.deleteById(u.getId_usuario());
            repo.save(userUpt);
            return new ResponseEntity(userUpt, HttpStatus.OK);
        }
        return new ResponseEntity("Usuário não encontrado", HttpStatus.NOT_FOUND);
    }

}
