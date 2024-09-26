package br.ifsp.edu.trabalho.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifsp.edu.trabalho.model.domain.Musica;

public interface MusicaDAO extends JpaRepository<Musica, Long>{
    public List<Musica> findByNomeContainingIgnoreCase(String query);

}
