package br.edu.fatecfranca.sistema_juridico.service;

import br.edu.fatecfranca.sistema_juridico.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // O "username" que recebemos aqui é o CPF que o usuário digitou na tela de login
        return clienteRepository.findByCpf(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado com o CPF: " + username));
    }
}

