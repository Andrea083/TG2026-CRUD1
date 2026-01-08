/*
simular o uso de Inteligência Artificial para "traduzir" o "juridiquês",
O método traduzirTexto(String textoJuridico) recebe um texto técnico e
faz uma substituição muito simples de palavras.
Em um projeto real, aqui haveria a chamada para um modelo complexo
de Processamento de Linguagem Natural (NLP).
 */

package br.edu.fatecfranca.sistema_juridico.service;

import org.springframework.stereotype.Service;

@Service
public class MockNLPService {

    // Método que SIMULA a tradução do "juridiquês"
    public String traduzirTexto(String textoJuridico) {
        System.out.println("SIMULANDO: Traduzindo texto jurídico...");
        if (textoJuridico.contains("DEFERIDO")) {
            return "BOA NOTÍCIA: O juiz aceitou o pedido feito pelo seu advogado.";
        }
        if (textoJuridico.contains("INDEFERIDO")) {
            return "ATENÇÃO: O juiz negou um pedido feito no seu processo. Seu advogado irá analisar os próximos passos.";
        }
        return "Houve uma nova movimentação no seu processo. Em breve daremos mais detalhes.";
    }
}

