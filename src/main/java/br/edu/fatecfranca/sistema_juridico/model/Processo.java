/*
@ManyToOne: Define o relacionamento: Muitos processos podem pertencer a Um cliente.
@JoinColumn(name = "cliente_id"): Cria uma coluna na tabela processo chamada cliente_id, que será a chave estrangeira para ligar ao id do cliente.
*/

package br.edu.fatecfranca.sistema_juridico.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "processo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numero;

    private String reclamante;
    private String reclamada;
    private String assunto;

    @Temporal(TemporalType.DATE)
    private Date dataAbertura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advogado_id", nullable = false)
    private Advogado advogado;
}
