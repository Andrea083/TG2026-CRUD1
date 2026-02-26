//criar os endpoints que o frontend irá consumir.

package br.edu.fatecfranca.sistema_juridico.controller;

import br.edu.fatecfranca.sistema_juridico.model.Movimentacao;
import br.edu.fatecfranca.sistema_juridico.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    // Endpoint para LISTAR todas as movimentações de um processo
    @GetMapping("/processo/{processoId}")
    public ResponseEntity<List<Movimentacao>> buscarPorProcesso(@PathVariable Long processoId, Principal principal) {
        String cpf = principal.getName(); // Pega o CPF do usuário logado
        List<Movimentacao> movimentacoes = movimentacaoService.buscarMovimentacoesPorProcesso(processoId);
        return ResponseEntity.ok(movimentacoes);
    }

    // Endpoint para ADICIONAR uma nova movimentação (simulada) a um processo
    @PostMapping("/processo/{processoId}")
    public ResponseEntity<Movimentacao> adicionarMovimentacao(@PathVariable Long processoId, Principal principal) {
        String cpf = principal.getName(); // Pega o CPF do usuário logado
        Movimentacao novaMovimentacao = movimentacaoService.adicionarMovimentacao(processoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMovimentacao);
    }
}
