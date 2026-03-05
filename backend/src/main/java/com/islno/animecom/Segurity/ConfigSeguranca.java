package com.islno.animecom.Segurity;

import com.islno.animecom.Service.AutenticacaoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigSeguranca {
    @Bean
    public SecurityFilterChain FiltroDeSeguranca(HttpSecurity http) throws Exception{
        http
                // 1. O QUE É PÚBLICO E O QUE É BLOQUEADO?
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/anime/**", "/buscar", "/api/popup").permitAll() // Liberado para todos
                        .requestMatchers("/images/**", "/css/**", "/js/**").permitAll() // Imagens e estilos liberados
                        .requestMatchers("/cadastro", "/login").permitAll() // Telas de acesso liberadas
                        .anyRequest().authenticated() // Qualquer outra URL exige login!
                )
                // 2. CONFIGURAÇÃO DA NOSSA TELA DE LOGIN
                .formLogin(login -> login
                        .loginPage("/login") // Avisa o Spring que nós temos nossa própria tela de login
                        .defaultSuccessUrl("/", true) // Para onde ir se a senha estiver certa (Home)
                        .permitAll()
                )
                // 3. CONFIGURAÇÃO DO BOTÃO "SAIR"
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/") // Volta pra Home ao sair
                        .permitAll()
                );

        return http.build();
    }

    // 4. O "LIQUIDIFICADOR" DE SENHAS
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(AutenticacaoService service, PasswordEncoder encoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(service);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }
}