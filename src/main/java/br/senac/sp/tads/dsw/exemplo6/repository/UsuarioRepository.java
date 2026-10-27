package br.senac.sp.tads.dsw.exemplo6.repository;

import br.senac.sp.tads.dsw.exemplo6.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    boolean existsByLogin(String login);
    Optional<UserDetails> findByLogin(String login);
}
