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
        Page<AnimeModel> dadosDaPagina = animeService.listarPaginado(page);

        model.addAttribute("listaDeAnimes", dadosDaPagina);
        model.addAttribute("paginaAtual", page);
        model.addAttribute("totalPaginas", dadosDaPagina.getTotalPages());

        model.addAttribute("listaPopulares", animeService.buscarTop10());
        return "home";
    }


    @GetMapping("/api/popup")
    @ResponseBody
    public List<AnimeModel> dadosDoPopup(@RequestParam String termo) {
        if (termo.length() < 2) return List.of();
        return animeService.buscarParaPopup(termo);
    }

    @GetMapping("/buscar")
    public String paginaDeBusca(@RequestParam("titulo") String termo,
                                @RequestParam(defaultValue = "0") int page,
                                Model model) {

        Page<AnimeModel> resultados = animeService.filtrarAnimes(termo, page);

        model.addAttribute("listaDeAnimes", resultados);
        model.addAttribute("termoBuscado", termo);
        model.addAttribute("paginaAtual", page);
        model.addAttribute("totalPaginas", resultados.getTotalPages());

        return "busca";
    }
    @GetMapping("/anime/{identificador}")
    public String paginaDetalhes(@PathVariable("identificador") String url, Model model) {

        try {

            String idString = url.split("-")[0];

            Long id = Long.parseLong(idString);

            AnimeModel anime = animeService.buscarPorId(id);

            if (anime != null) {
                model.addAttribute("anime", anime);
                return "detalhes";
            }
        } catch (NumberFormatException e) {

            return "redirect:/";
        }

        return "redirect:/";
    }

}