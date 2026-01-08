package br.edu.fatecfranca.sistema_juridico.service;

import br.edu.fatecfranca.sistema_juridico.model.Cliente;
import br.edu.fatecfranca.sistema_juridico.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injetamos o codificador de senhas

    public Cliente cadastrarCliente(Cliente cliente) {
        // Antes de salvar, criptografamos a senha do cliente
        cliente.setSenha(passwordEncoder.encode(cliente.getPassword()));
        return clienteRepository.save(cliente);
    }
}
