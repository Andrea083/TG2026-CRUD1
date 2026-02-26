package br.edu.fatecfranca.sistema_juridico.repository;

import br.edu.fatecfranca.sistema_juridico.model.Advogado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvogadoRepository extends JpaRepository<Advogado, Long> {
}

