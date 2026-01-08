/*
@Entity: Avisa ao JPA (a tecnologia de persistência) que a classe Cliente corresponde a uma tabela no banco.
@Table(name = "cliente"): Especifica que o nome da tabela no PostgreSQL será "cliente".
@Id e @GeneratedValue: Marcam o campo id como a chave primária da tabela, que será gerada automaticamente.
@Getter, @Setter, etc.: São do Lombok, criam os métodos get/set e construtores, limpando o código.
*/

package br.edu.fatecfranca.sistema_juridico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//Para a classe Cliente ser "entendida" pelo Spring Security, ela deve
//implementar a interface UserDetails
public class Cliente implements UserDetails { // Implementa a interface UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ... (todos os outros campos como nome, email, telefone, etc. continuam aqui)
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    private String telefone;
    private String endereco;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;


    //--- MÉTODOS EXIGIDOS PELO SPRING SECURITY ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Para este sistema simples, o cliente não tem "papéis" (como ADMIN, USER).
        // Retornamos uma lista vazia.
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        // Retorna a senha do cliente.
        return this.senha;
    }

    @Override
    public String getUsername() {
        // Usaremos o CPF como "username" para o login.
        return this.cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // A conta nunca expira.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // A conta nunca é bloqueada.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // As credenciais nunca expiram.
    }

    @Override
    public boolean isEnabled() {
        return true; // A conta está sempre habilitada.
    }
}

