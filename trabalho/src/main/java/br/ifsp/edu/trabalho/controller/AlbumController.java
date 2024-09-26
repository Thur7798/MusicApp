package br.ifsp.edu.trabalho.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ifsp.edu.trabalho.model.dao.AlbumDAO;
import br.ifsp.edu.trabalho.model.domain.Album;
import br.ifsp.edu.trabalho.model.domain.Musica;
import br.ifsp.edu.trabalho.service.MusicaService;

@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumDAO adao;

    @Autowired MusicaService service;

    @GetMapping("/cadastrar")
    public String cadastrar(Album album){
        return "album/cadastro";
    }

    @PostMapping("/salvar")
    public String cadastrar(@RequestParam(name="nome") String nome, @RequestParam(name="musicas") List<MultipartFile> musicas){
        List<Musica> Musicas=new ArrayList<>();
        Album album=new Album(nome);
        adao.save(album);
        for(MultipartFile musica:musicas){
            try{
                Musica mservice=service.saveFile(musica, musica.getOriginalFilename());
                mservice.setAlbum(album);
                Musicas.add(mservice);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
        album.setMusicas(Musicas);
        adao.save(album);
        return ("redirect:/album/cadastrar");
    }

    @GetMapping("/listar")
    public List<Album> listar(){
        return adao.findAll();
    }

    @PostMapping("/alterar")
    public Album alterar(@RequestParam(name="nome") String nome, @RequestParam(name="id") Long Id){
        Album album=adao.getReferenceById(Id);
        album.setNome(nome);
        return adao.save(album);
    }

    @DeleteMapping("/deletar")
    public void deletar(@PathVariable(name="id") Long id){
        if(adao.existsById(id)){
            adao.deleteById(id);
        }else{
            return;
        }
    }

    @GetMapping("/musicas/{id}")
    public String listarMusicas(@PathVariable Long id, ModelMap map){
        Album album=adao.getReferenceById(id);
        map.addAttribute("album", album);
        return "fragments/musicasAlbum :: musicas";
    }
}
