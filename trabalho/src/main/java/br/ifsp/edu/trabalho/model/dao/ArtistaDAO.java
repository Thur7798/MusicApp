package br.ifsp.edu.trabalho.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifsp.edu.trabalho.model.domain.Artista;

public interface ArtistaDAO extends JpaRepository<Artista, Long>{
    public List<Artista> findByNomeArtisticoContainingIgnoreCase(String query);

}
