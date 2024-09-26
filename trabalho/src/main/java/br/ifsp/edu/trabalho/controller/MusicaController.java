package br.ifsp.edu.trabalho.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

import br.ifsp.edu.trabalho.model.dao.MusicaDAO;
import br.ifsp.edu.trabalho.model.domain.Musica;
import br.ifsp.edu.trabalho.service.MusicaService;

@Controller
@RequestMapping("/musica")
public class MusicaController {

    @Autowired
    private MusicaService service;

    @Autowired
    private MusicaDAO mdao;

    @PostMapping("/salvar")
    public String salvar(@RequestParam(name = "nome") String nome, @RequestParam(name="arquivo") MultipartFile file) {
        try {
            Musica musica = service.saveFile(file, nome);
            mdao.save(musica);
            return "redirect:/musica/cadastrar";
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/musica/cadastrar?error";
        }
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Musica musica) {
        return "musica/cadastro";
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getMusica(@PathVariable Long id) {
        Musica musica = service.getFile(id);

        if (musica == null || musica.getArquivo() == null) {
            return ResponseEntity.notFound().build();
        }

        // Retorna o arquivo como um stream de bytes
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + musica.getNome() + "\"")
                .body(musica.getArquivo());
    }

    

}
