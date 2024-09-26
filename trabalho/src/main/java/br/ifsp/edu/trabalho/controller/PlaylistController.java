package br.ifsp.edu.trabalho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ifsp.edu.trabalho.model.dao.MusicaDAO;
import br.ifsp.edu.trabalho.model.dao.PlaylistDAO;
import br.ifsp.edu.trabalho.model.domain.Musica;
import br.ifsp.edu.trabalho.model.domain.Playlist;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistDAO pdao;

    @Autowired
    private MusicaDAO mdao;

    @GetMapping("/cadastrar")
    public String cadastrar(Playlist playlist) {
        return "playlist/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam(name = "nome") String nome) {
        pdao.save(new Playlist(nome));
        return ("redirect:/playlist/cadastrar");
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        List<Playlist> playlists = pdao.findAll();
        model.addAttribute("playlists", playlists);
        return "fragments/playlists :: playlist";

    }

    @PostMapping("/alterar")
    public Playlist alterar(@RequestParam(name = "nome") String nome, @RequestParam(name = "id") Long Id) {
        Playlist playlist = pdao.getReferenceById(Id);
        playlist.setNome(nome);
        return pdao.save(playlist);
    }

    @DeleteMapping("/deletar")
    public void deletar(@PathVariable(name = "id") Long id) {
        if (pdao.existsById(id)) {
            pdao.deleteById(id);
        } else {
            return;
        }
    }

    @PostMapping("/adicionarMusica")
    public ResponseEntity<String> adicionarMusica(@RequestParam(name = "id") Long id,
            @RequestParam(name = "id_musica") Long idMusica) {
        Musica musica = mdao.getReferenceById(idMusica);
        Playlist playlist = pdao.getReferenceById(id);
        List<Musica> musicas = playlist.getMusicas();
        musicas.add(musica);
        pdao.save(playlist);

        // Retorna uma resposta simples
        return ResponseEntity.ok("Música adicionada com sucesso!");
    }

    @GetMapping("/listarPlaylist")
    public String listarPlaylists(ModelMap map) {
        List<Playlist> playlists = pdao.findAll();
        map.addAttribute("playlists", playlists);
        return "fragments/select :: select";
    }

    @GetMapping("/musicas/{id}")
    public String listarMusicas(@PathVariable Long id, ModelMap map){
        Playlist playlist=pdao.getReferenceById(id);
        map.addAttribute("playlist", playlist);
        return "fragments/musicas :: musicas";
    }

}
