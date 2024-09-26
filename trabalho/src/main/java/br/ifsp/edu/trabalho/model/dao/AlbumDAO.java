package br.ifsp.edu.trabalho.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifsp.edu.trabalho.model.domain.Album;


public interface AlbumDAO extends JpaRepository<Album, Long>{
    public List<Album> findByNomeContainingIgnoreCase(String query);
}
