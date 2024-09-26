package br.ifsp.edu.trabalho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ifsp.edu.trabalho.model.dao.AlbumDAO;
import br.ifsp.edu.trabalho.model.dao.ArtistaDAO;
import br.ifsp.edu.trabalho.model.dao.MusicaDAO;
import br.ifsp.edu.trabalho.model.domain.Album;
import br.ifsp.edu.trabalho.model.domain.Artista;
import br.ifsp.edu.trabalho.model.domain.Musica;

@Controller
public class PesquisaController {
    @Autowired
    private AlbumDAO aldao;
    
    @Autowired
    private ArtistaDAO adao;

    @Autowired
    private MusicaDAO mdao;

    @GetMapping("/pesquisar")
    public String pesquisar(@RequestParam("query") String query, ModelMap model) {
        List<Artista> artista = adao.findByNomeArtisticoContainingIgnoreCase(query);
        List<Album> album = aldao.findByNomeContainingIgnoreCase(query);
        List<Musica> musicas = mdao.findByNomeContainingIgnoreCase(query);
    
        model.addAttribute("artista", artista);  
        model.addAttribute("album", album);      
        model.addAttribute("musica", musicas);   
        
        return "fragments/pesquisa :: resultados";
    }


}
