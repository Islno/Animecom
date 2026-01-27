package com.islno.animecom.Repository;

import com.islno.animecom.Model.AnimeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnimecomRepository extends JpaRepository<AnimeModel, Long> {

    // Busca personalizada
    List<AnimeModel> findByTituloContainingIgnoreCase(String titulo);
}