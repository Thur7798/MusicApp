package br.ifsp.edu.trabalho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.ifsp.edu.trabalho.model.dao.MusicaDAO;
import br.ifsp.edu.trabalho.model.domain.Musica;

import java.io.IOException;

@Service
public class MusicaService {

    @Autowired
    private MusicaDAO mdao;

    public Musica saveFile(MultipartFile file, String nome) throws IOException {
        Musica musica = new Musica();
        musica.setNome(nome);
        musica.setArquivo(file.getBytes()); // Converte o arquivo para bytes e armazena

        return musica;
    }

    public Musica getFile(Long id) {
        return mdao.findById(id).orElse(null);
    }
}
