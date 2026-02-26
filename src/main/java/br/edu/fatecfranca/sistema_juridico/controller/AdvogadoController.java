package br.edu.fatecfranca.sistema_juridico.controller;

import br.edu.fatecfranca.sistema_juridico.model.Advogado;
import br.edu.fatecfranca.sistema_juridico.repository.AdvogadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advogados")
public class AdvogadoController {

    @Autowired
    private AdvogadoRepository advogadoRepository;

    // Endpoint para cadastrar um novo advogado
    @PostMapping
    public ResponseEntity<Advogado> cadastrarAdvogado(@RequestBody Advogado advogado) {
        // Em um projeto real, aqui deve fazer a criptografia da senha
        // ex: advogado.setSenha(passwordEncoder.encode(advogado.getSenha()));
        Advogado novoAdvogado = advogadoRepository.save(advogado);
        return new ResponseEntity<>(novoAdvogado, HttpStatus.CREATED);
    }

    // Endpoint para listar todos os advogados
    @GetMapping
    public List<Advogado> listarAdvogados() {
        return advogadoRepository.findAll();
    }
}

