package com.fotoflow.fotoflowApi.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register").permitAll()

                        // ROTAS DE FOTÓGRAFO
                        .requestMatchers(HttpMethod.GET, "/fotografos/**").hasAnyRole("FOTOGRAFO", "CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/fotografos/**").hasRole("FOTOGRAFO")
                        .requestMatchers(HttpMethod.PUT, "/fotografos/**").hasRole("FOTOGRAFO")
                        .requestMatchers(HttpMethod.DELETE, "/fotografos/**").hasRole("FOTOGRAFO")

                        // ROTAS DE CLIENTE
                        .requestMatchers(HttpMethod.GET, "/clientes/**").hasAnyRole("FOTOGRAFO", "CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/clientes/**").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/clientes/**").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/clientes/**").hasRole("CLIENTE")

                        // ROTAS DE ALBUM
                        .requestMatchers(HttpMethod.GET, "/album/**").hasAnyRole("FOTOGRAFO", "CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/album/**").hasRole("FOTOGRAFO")
                        .requestMatchers(HttpMethod.DELETE, "/album/**").hasRole("FOTOGRAFO")
                        .requestMatchers(HttpMethod.PUT, "/album/**").hasRole("FOTOGRAFO")

                        //ROTAS DE FOTOS
                        .requestMatchers(HttpMethod.GET, "/fotos/**").hasAnyRole("FOTOGRAFO", "CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/fotos/**").hasRole("FOTOGRAFO")
                        .requestMatchers(HttpMethod.DELETE, "/fotos/**").hasRole("FOTOGRAFO")
                        .requestMatchers(HttpMethod.PUT, "/fotos/**").hasRole("FOTOGRAFO")

                        // ROTAS DE PAGAMENTOS
                        // --

                        .anyRequest().authenticated()
                )
                .addFilter(new BasicAuthenticationFilter(authenticationManager(http)))
                .formLogin(form -> form
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/usuarios", true)
                        .permitAll())
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

        return auth.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Usa senhas em texto puro (não recomendado para produção)
        return NoOpPasswordEncoder.getInstance();
    }

}
