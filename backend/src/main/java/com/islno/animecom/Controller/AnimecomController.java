package com.islno.animecom.Controller;

import com.islno.animecom.Model.AnimeModel;
import com.islno.animecom.Repository.AnimecomRepository;
import com.islno.animecom.Service.AnimeService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class AnimecomController {

@Autowired
    private final AnimecomRepository animecomRepository;
    private final AnimeService animeService;

    public AnimecomController(AnimecomRepository animecomRepository, AnimeService animeService) {
        this.animecomRepository = animecomRepository;
        this.animeService = animeService;
    }

    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "0") int page, Model model) {
        // Chama o service pedindo a página X
        Page<AnimeModel> dadosDaPagina = animeService.listarPaginado(page);

        model.addAttribute("listaDeAnimes", dadosDaPagina);
        model.addAttribute("paginaAtual", page);
        model.addAttribute("totalPaginas", dadosDaPagina.getTotalPages());

        return "home";
    }

    // ROTA "INVISÍVEL" (O Javascript do Popup chama essa aqui)
    @GetMapping("/api/popup")
    @ResponseBody // <--- Importante: Retorna DADOS (JSON), não HTML
    public List<AnimeModel> dadosDoPopup(@RequestParam String termo) {
        if (termo.length() < 2) return List.of(); // Só busca se tiver 2 letras
        return animeService.buscarParaPopup(termo);
    }

    // ROTA DE DETALHES (Para onde você vai ao clicar)
    @GetMapping("/anime/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        AnimeModel anime = animeService.buscarPorId(id);
        if (anime != null) {
            model.addAttribute("anime", anime);
            return "detalhes";
        }
        return "redirect:/";
    }
}