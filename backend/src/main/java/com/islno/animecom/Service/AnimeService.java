package com.islno.animecom.Service;

import com.islno.animecom.Model.AnimeModel;
import com.islno.animecom.Repository.AnimecomRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeService {
    @Autowired
    private AnimecomRepository animecomRepository;

    public Page<AnimeModel> listarPaginado(int numeroDaPagina) {
        PageRequest regra = PageRequest.of(numeroDaPagina, 20, Sort.by("titulo").ascending());
        return animecomRepository.findAll(regra);
    }

    public List<AnimeModel> buscarParaPopup(String digitado) {
        PageRequest regra = PageRequest.of(0, 5, Sort.by("titulo").ascending());

        return animecomRepository.findByTituloContainingIgnoreCase(digitado, regra).getContent();
    }
    public AnimeModel buscarPorId(Long id) {
        return animecomRepository.findById(id).orElse(null);
    }
}
