package com.islno.animecom.Controller;

import com.islno.animecom.Model.UsuarioModel;
import com.islno.animecom.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CadastroController {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/cadastro")
    public String paginaCadastro(){
        return "cadastro";
    }
    @PostMapping("/cadastro")
    public String salvarUsuario(UsuarioModel usuario) {

        // 1. Encriptar a palavra-passe antes de guardar!
        String senhaEncriptada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaEncriptada);

        // 2. Guardar o utilizador na base de dados
        repository.save(usuario);

        // 3. Redirecionar para a página de login com uma mensagem de sucesso
        return "redirect:/login?cadastrado=true";
    }
}
