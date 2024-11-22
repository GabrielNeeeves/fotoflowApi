package com.fotoflow.fotoflowApi.controller.usuarios;

import com.fotoflow.fotoflowApi.model.usuarios.LoginDto;
import com.fotoflow.fotoflowApi.model.usuarios.UsuarioDto;
import com.fotoflow.fotoflowApi.model.usuarios.UsuarioModel;
import com.fotoflow.fotoflowApi.repository.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    //AUTENTICAR USUARIO
    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());

        //autentica o Token
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // Se a autenticação for bem-sucedida, o usuário será autenticado automaticamente
        if(authentication.isAuthenticated()) {
            return "Authentication successful";
        }

        return "Authentication failed";
    }

    //CRIAR USUARIO
    @PostMapping("/register")
    public String register(@RequestBody UsuarioDto dto) {

        //VERIFICA SE USUARIO JÁ EXISTE
        if(repo.findByEmail(dto.email()).isPresent()) {
            return "Email já cadastrado";
        }

        //CRIA UM NOVO USUARIO
        String senhaCrip = passwordEncoder.encode(dto.senha());
        UsuarioModel nvUsuario = new UsuarioModel(dto);
        nvUsuario.setSenha(senhaCrip);

        repo.save(nvUsuario);

        return "Usuario registrado";
    }

}
