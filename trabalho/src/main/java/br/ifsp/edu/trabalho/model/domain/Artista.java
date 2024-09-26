package br.ifsp.edu.trabalho.model.domain;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="Artista")
public class Artista extends Usuario{

    public Artista(String nome, String senha, String email) {
        super(nome, senha, email);
    }

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Musica> musicas;
    
}
