package br.ifsp.edu.trabalho.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ifsp.edu.trabalho.model.dao.ArtistaDAO;
import br.ifsp.edu.trabalho.model.dao.UsuarioDAO;
import br.ifsp.edu.trabalho.model.domain.Artista;
import br.ifsp.edu.trabalho.model.domain.Usuario;



@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDAO udao;

    @Autowired
    private ArtistaDAO adao;

    @GetMapping("/cadastrar")
    public String cadastrar(Usuario usuario) {
        return "usuario/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(Usuario usuario, @RequestParam(name="conta") String conta, @RequestParam(name="nome_artistico", required =false) String nome_artistico){
        if(conta.equals("artista")){
            Artista artista=new Artista(usuario.getNome(), usuario.getSenha(), usuario.getEmail());
            artista.setNomeArtistico(nome_artistico);
            adao.save(artista);
        }else{
            udao.save(usuario);
        }
        return ("redirect:/usuario/cadastrar");
    }

    @GetMapping("/listar")
    public List<Usuario> listar() {
        return udao.findAll();
    }

    @PostMapping("/alterar")
    public Usuario alterar(@RequestParam(name = "id") Long id, @RequestParam(name = "nome") String nome, @RequestParam(name = "senha") String senha, @RequestParam(name = "email") String email) {
        // Professor p = daop.findById(id).get();
        Usuario u = udao.getReferenceById(id);
        u.setNome(nome);
        return udao.save(u);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable(name="id") Long id) {
        if(udao.existsById(id)){
            udao.deleteById(id);
        }else{
            return;
        }
    }

    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }

    






}
