/*
simular a busca de dados na AASP, retornando dados fictícios.
Em um projeto real, substituir pelo código de web scraping.
coletarNovasMovimentacoes(String numeroProcesso) simula que foi até a AASP,
encontrou uma nova atualização para um processo e a retorna.
Os dados são fixos ("hardcoded") apenas para testar o fluxo.
 */

package br.edu.fatecfranca.sistema_juridico.service;

import br.edu.fatecfranca.sistema_juridico.model.Movimentacao;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MockAASPService {

    // Método que SIMULA a coleta de uma nova movimentação na AASP
    public String coletarNovaMovimentacao(String numeroProcesso) {
        // Para fins de teste, retorna sempre um texto fixo
        System.out.println("SIMULANDO: Coletando dados para o processo " + numeroProcesso);
        return "DEFERIDO o pedido de antecipação de tutela, nos termos da fundamentação.";
    }
}

