package com.islno.animecom.Service;

import com.islno.animecom.Model.UsuarioModel;
import com.islno.animecom.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("🚨 [LOGIN] Alguém tentou logar com o email: " + email);

        Optional<UsuarioModel> usuarioOpt = repository.findByEmail(email);

        if (usuarioOpt.isEmpty()) {
            System.out.println("🚨 [LOGIN] Email não encontrado no banco!");
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        UsuarioModel usuario = usuarioOpt.get();
        System.out.println("🚨 [LOGIN] Usuário encontrado! A senha dele no banco é: " + usuario.getSenha());

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles("USER")
                .build();
    }
}