package br.ifsp.edu.trabalho.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifsp.edu.trabalho.model.domain.Usuario;


public interface UsuarioDAO extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByEmail(String email);
}
