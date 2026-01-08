/*
Esta classe guarda cada atualização de um processo, incluindo a "tradução" para linguagem leiga.
@Column(columnDefinition = "TEXT"): Garante que o campo no banco de dados aceite textos bem longos, que são comuns nas descrições de movimentações.
textoTraduzido: Este é o campo-chave que atenderá ao requisito RF007, guardando a informação em termos simples.
*/
package br.edu.fatecfranca.sistema_juridico.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "movimentacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dataMovimentacao;

    @Column(columnDefinition = "TEXT")
    private String textoOriginal;

    @Column(columnDefinition = "TEXT")
    private String textoTraduzido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processo_id", nullable = false)
    private Processo processo;
}
