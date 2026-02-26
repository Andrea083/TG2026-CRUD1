/*
Classe como um componente de serviço do Spring. Regras de negócio.
private final ProcessoRepository processoRepository;: Estou declarando que o
o serviço precisa de um ProcessoRepository para funcionar.
@Autowired: O Spring vai "injetar" (fornecer) automaticamente uma
instância do ProcessoRepository.
public List<Processo> buscarProcessosPorCpf(String cpf): Este é o
primeiro método de negócio (recebe um CPF, chama o método criado no
ProcessoRepository e retorna a lista de processos daquele cliente específico.
Isso atende diretamente a regra de que o cliente só pode ver seus dados.
 */

package br.edu.fatecfranca.sistema_juridico.service;

import br.edu.fatecfranca.sistema_juridico.model.Advogado;
import br.edu.fatecfranca.sistema_juridico.model.Processo;
import br.edu.fatecfranca.sistema_juridico.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    // 1. MÉTODO PRIVADO PARA SIMULAR O LOGIN
    // Centraliza a lógica de simulação em um único lugar.
    private Advogado getAdvogadoAutenticadoSimulado() {
        Advogado advogado = new Advogado();
        advogado.setId(1L); // Assumindo que o advogado logado tem ID 1
        return advogado;
    }

    // cadastrar
    public Processo cadastrarProcesso(Processo novoProcesso) {
        Advogado advogadoAutenticado = getAdvogadoAutenticadoSimulado(); // Usa o método privado
        novoProcesso.setAdvogado(advogadoAutenticado);
        return processoRepository.save(novoProcesso);
    }

    // buscar processo
    public Processo findById(Long id) {
        Advogado advogadoAutenticado = getAdvogadoAutenticadoSimulado(); // Usa o método privado (CORRIGIDO)

        Processo processo = processoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Processo não encontrado"));

        if (!processo.getAdvogado().getId().equals(advogadoAutenticado.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado.");
        }
        return processo;
    }

    // buscar cliente(cpf)
    public List<Processo> buscarProcessosPorCpf(String cpf) {
        // Aqui não precisa de checagem por enquanto
        return processoRepository.findByClienteCpf(cpf);
    }

    // atualizar
    public Processo atualizarProcesso(Long id, Processo dadosAtualizacao) {
        // A chamada ao 'findById' agora funciona e já faz a checagem de segurança
        Processo processoExistente = this.findById(id);

        processoExistente.setNumero(dadosAtualizacao.getNumero());
        processoExistente.setReclamante(dadosAtualizacao.getReclamante());
        processoExistente.setReclamada(dadosAtualizacao.getReclamada());
        processoExistente.setAssunto(dadosAtualizacao.getAssunto());
        processoExistente.setDataAbertura(dadosAtualizacao.getDataAbertura());

        return processoRepository.save(processoExistente);
    }

    // deletar
    public void deletarProcesso(Long id) {
        this.findById(id); // A chamada para checagem de segurança agora funciona
        processoRepository.deleteById(id);
    }
}


