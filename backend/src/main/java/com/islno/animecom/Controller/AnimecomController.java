package com.islno.animecom.Controller;

import com.islno.animecom.Model.AnimeModel;
import com.islno.animecom.Repository.AnimecomRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimecomController {
    private final AnimecomRepository animecomRepository;

    public AnimecomController(AnimecomRepository animecomRepository) {
        this.animecomRepository = animecomRepository;
    }

    @GetMapping("/home")
    public String home(){
        return "Bem vindo ao Animecom!";
    }
    @GetMapping("/buscar")
    public List<AnimeModel> buscarAnimes(@RequestParam String titulo){
        return animecomRepository.findByTituloContainingIgnoreCase(titulo);
    }
}
