//gerencia as requisições relacionadas a processos.

package br.edu.fatecfranca.sistema_juridico.controller;

import br.edu.fatecfranca.sistema_juridico.model.Processo;
import br.edu.fatecfranca.sistema_juridico.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal; // IMPORTAR
import java.util.List;

@RestController
@RequestMapping("/processos") // URL base atualizada
public class ProcessoController {

    private final ProcessoService processoService;

    @Autowired
    public ProcessoController(ProcessoService processoService) {
        this.processoService = processoService;
    }

    //BUSCAR
    @GetMapping("/cliente/{cpf}")
    public ResponseEntity<List<Processo>> buscarProcessosPorCpfDoCliente(@PathVariable String cpf) {
        List<Processo> processos = processoService.buscarProcessosPorCpf(cpf);
        return ResponseEntity.ok(processos);
    }

    //CADASTRAR
    @PostMapping("/cadastrar")
    public ResponseEntity<Processo> cadastrarProcesso(@RequestBody Processo processo, Principal principal) {
        // O objeto 'Principal' é injetado pelo Spring Security e contém
        // as informações do usuário logado.
        String cpfDoUsuarioLogado = principal.getName();

        Processo novoProcesso = processoService.cadastrarProcesso(processo, cpfDoUsuarioLogado);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProcesso);
    }

    //ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Processo> atualizarProcesso(
            @PathVariable Long id,
            @RequestBody Processo dadosProcesso,
            Principal principal)
    {
        String cpf = principal.getName();
        Processo processoAtualizado = processoService.atualizarProcesso(id, dadosProcesso, cpf);
        return ResponseEntity.ok(processoAtualizado);
    }

    //DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProcesso(@PathVariable Long id, Principal principal) {
        String cpf = principal.getName();
        processoService.deletarProcesso(id, cpf);

        // Retorna uma resposta 204 No Content, que é o padrão para uma
        // operação de DELETE bem-sucedida, indicando que o recurso não existe mais.
        return ResponseEntity.noContent().build();
    }
}
