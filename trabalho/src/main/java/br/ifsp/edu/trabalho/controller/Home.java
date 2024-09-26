package br.ifsp.edu.trabalho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {
    @GetMapping("/")
    public String principal(){
        return "/home";
    }

}
