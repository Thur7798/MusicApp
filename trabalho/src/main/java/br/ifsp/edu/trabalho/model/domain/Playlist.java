package br.ifsp.edu.trabalho.model.domain;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="Playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(
        name = "playlist_musica",
        joinColumns = @JoinColumn(name = "playlist_id"),
        inverseJoinColumns = @JoinColumn(name = "musica_id")
    )
    private List<Musica> musicas;

    public Playlist(String nome) {
        this.nome = nome;
    }

    

}
