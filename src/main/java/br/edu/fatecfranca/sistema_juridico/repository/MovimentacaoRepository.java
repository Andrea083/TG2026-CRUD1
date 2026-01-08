/*
Gerencia as operações para a entidade Movimentacao
 */
package br.edu.fatecfranca.sistema_juridico.repository;

import br.edu.fatecfranca.sistema_juridico.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // IMPORTAR

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    // --- NOVO MÉTODO ---
    // Encontra todas as movimentações associadas a um ID de processo específico.
    List<Movimentacao> findByProcessoId(Long processoId);
}
