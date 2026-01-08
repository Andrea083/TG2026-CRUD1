/*
Controllers são classes que vão expor a lógica para o mundo exterior
através de URLs (endpoints de API)

 */
package br.edu.fatecfranca.sistema_juridico.controller;

import br.edu.fatecfranca.sistema_juridico.model.Cliente;
import br.edu.fatecfranca.sistema_juridico.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody Cliente cliente) {
    //"@RequestBody Cliente cliente" pega o JSON enviado no corpo da requisição e o converte em um objeto Cliente
        clienteService.cadastrarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso!");
    }
}

