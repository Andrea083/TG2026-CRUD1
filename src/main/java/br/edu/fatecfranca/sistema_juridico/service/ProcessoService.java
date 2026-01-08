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

import br.edu.fatecfranca.sistema_juridico.model.Cliente;
import br.edu.fatecfranca.sistema_juridico.model.Processo;
import br.edu.fatecfranca.sistema_juridico.repository.ClienteRepository; // IMPORTAR
import br.edu.fatecfranca.sistema_juridico.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoService {

    private final ProcessoRepository processoRepository;
    private final ClienteRepository clienteRepository; // ADICIONAR

    @Autowired
    public ProcessoService(ProcessoRepository processoRepository, ClienteRepository clienteRepository) { // ADICIONAR
        this.processoRepository = processoRepository;
        this.clienteRepository = clienteRepository; // ADICIONAR
    }

    public List<Processo> buscarProcessosPorCpf(String cpf) {
        return processoRepository.findByClienteCpf(cpf);
    }

    // MÉTODO CADASTRAR
    public Processo cadastrarProcesso(Processo processo, String cpfCliente) {
        // 1. Busca o cliente no banco de dados pelo CPF do usuário logado.
        Cliente cliente = clienteRepository.findByCpf(cpfCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        // 2. Associa o processo a esse cliente.
        processo.setCliente(cliente);

        // 3. Salva o processo no banco de dados.
        return processoRepository.save(processo);
    }
    // MÉTODO UPDATE
    public Processo atualizarProcesso(Long processoId, Processo dadosAtualizados, String cpfUsuarioLogado) {
        // 1. Primeiro, busca o processo no banco e verifica se o usuário é o dono.
        Processo processoExistente = verificarProprietarioDoProcesso(processoId, cpfUsuarioLogado);

        // 2. Atualiza os campos do processo existente com os novos dados.
        // É uma boa prática não permitir a alteração de certos campos, como o 'numero' ou o cliente associado.
        processoExistente.setReclamante(dadosAtualizados.getReclamante());
        processoExistente.setReclamada(dadosAtualizados.getReclamada());
        processoExistente.setAssunto(dadosAtualizados.getAssunto());
        processoExistente.setDataAbertura(dadosAtualizados.getDataAbertura());

        // 3. Salva o processo atualizado no banco de dados.
        return processoRepository.save(processoExistente);
    }

    // MÉTODO DELETE
    public void deletarProcesso(Long processoId, String cpfUsuarioLogado) {
        // 1. Verifica se o processo existe e se pertence ao usuário logado antes de deletar.
        // O método 'verificarProprietarioDoProcesso' já faz isso por nós.
        Processo processo = verificarProprietarioDoProcesso(processoId, cpfUsuarioLogado);

        // 2. Se a verificação passar, deleta o processo.
        processoRepository.deleteById(processo.getId());
    }

    /*
     Método de segurança privado para verificar se o usuário logado é o dono do processo.
     Evita que o usuário A veja os dados do processo do usuário B.
     */
    public Processo verificarProprietarioDoProcesso(Long processoId, String cpfUsuarioLogado) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado com o ID: " + processoId));

        if (!processo.getCliente().getCpf().equals(cpfUsuarioLogado)) {
            // Lança uma exceção de acesso negado se o CPF do dono do processo for
            // diferente do CPF do usuário que está logado.
            throw new AccessDeniedException("Acesso negado. Você não é o proprietário deste processo.");
        }
        return processo;
    }
}
