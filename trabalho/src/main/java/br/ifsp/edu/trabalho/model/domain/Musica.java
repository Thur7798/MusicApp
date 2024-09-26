package br.ifsp.edu.trabalho.model.domain;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="Musica")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Lob
    @Column(nullable = false)
    private byte[] arquivo;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToMany(mappedBy = "musicas")
    private List<Playlist> playlist;

    @ManyToOne
    @JoinColumn(name="artista_id")
    private Artista artista;



}
