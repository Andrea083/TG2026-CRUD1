package br.edu.fatecfranca.sistema_juridico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data // Anotação mágica do Lombok: inclui @Getter, @Setter, @ToString, @EqualsAndHashCode
@NoArgsConstructor // Cria um construtor sem argumentos (necessário para JPA)
@AllArgsConstructor // Cria um construtor com todos os argumentos
public class Advogado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, unique = true)
    private String numeroOAB;

    @OneToMany(mappedBy = "advogado")
    private List<Processo> processos;
}


