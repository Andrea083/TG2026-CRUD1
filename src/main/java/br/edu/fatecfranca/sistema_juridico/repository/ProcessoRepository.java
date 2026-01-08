/*
vai gerenciar as operações para a entidade Processo.
List<Processo> findByClienteCpf(String cpf); O Spring Data JPA cria uma
consulta que busca todos os processos (List<Processo>) pertencentes a um
cliente com um CPF específico. Essencial para garantir que
um cliente só veja seus próprios processos.
 */

package br.edu.fatecfranca.sistema_juridico.repository;

import br.edu.fatecfranca.sistema_juridico.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    // Método para buscar todos os processos de um cliente específico pelo CPF
    List<Processo> findByClienteCpf(String cpf);
}

