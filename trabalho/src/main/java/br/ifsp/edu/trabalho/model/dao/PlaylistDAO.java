package br.ifsp.edu.trabalho.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifsp.edu.trabalho.model.domain.Playlist;



public interface PlaylistDAO extends JpaRepository<Playlist, Long>{

}
