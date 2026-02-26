//contém a lógica de negócio, incluindo a verificação de segurança
// e a orquestração dos serviços simulados de AASP e NLP

package br.edu.fatecfranca.sistema_juridico.service;

import br.edu.fatecfranca.sistema_juridico.model.Movimentacao;
import br.edu.fatecfranca.sistema_juridico.model.Processo;
import br.edu.fatecfranca.sistema_juridico.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    // Injeta o serviço de processo para usar seus métodos públicos.
    @Autowired
    private ProcessoService processoService;

    @Autowired
    private MockAASPService mockAASPService;
    @Autowired
    private MockNLPService mockNLPService;

    public List<Movimentacao> buscarMovimentacoesPorProcesso(Long processoId) {
        // Chama o método público de ProcessoService para fazer a verificação.
        Processo processo = processoService.findById(processoId);
        return movimentacaoRepository.findByProcessoId(processo.getId());
    }

    public Movimentacao adicionarMovimentacao(Long processoId) {
        // Verificação delegada para o serviço responsável.
        Processo processo = processoService.findById(processoId);

        String textoOriginal = mockAASPService.coletarNovaMovimentacao(processo.getNumero());
        String textoTraduzido = mockNLPService.traduzirTexto(textoOriginal);

        Movimentacao novaMovimentacao = new Movimentacao();
        novaMovimentacao.setProcesso(processo);
        novaMovimentacao.setDataMovimentacao(new Date());
        novaMovimentacao.setTextoOriginal(textoOriginal);
        novaMovimentacao.setTextoTraduzido(textoTraduzido);

        return movimentacaoRepository.save(novaMovimentacao);
    }

}
