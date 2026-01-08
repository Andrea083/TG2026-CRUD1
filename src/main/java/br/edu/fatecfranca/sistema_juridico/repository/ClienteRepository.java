/*
extends JpaRepository: herda todos os métodos CRUD prontos (save, findById, findAll, deleteById, etc.).
<Cliente, Long>: Informa ao JpaRepository que esta interface vai gerenciar a entidade Cliente, e que o tipo da chave primária (Id) dessa entidade é Long.
*/

package br.edu.fatecfranca.sistema_juridico.repository;
import br.edu.fatecfranca.sistema_juridico.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Método para buscar um cliente pelo CPF, será útil para o login
    Optional<Cliente> findByCpf(String cpf);
}

