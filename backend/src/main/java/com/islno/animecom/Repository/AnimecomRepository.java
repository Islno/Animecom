package com.islno.animecom.Repository;

import com.islno.animecom.Model.AnimeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimecomRepository extends JpaRepository<AnimeModel, Long> {

    @Query("SELECT a FROM AnimeModel a WHERE LOWER(a.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) " +
            "ORDER BY CASE WHEN LOWER(a.titulo) " +
            "LIKE LOWER(CONCAT(:termo, '%')) " +
            "THEN 0 ELSE 1 END, a.titulo ASC")
    Page<AnimeModel> buscarComPrioridade(@Param("termo") String termo, Pageable pageable);

    @Query("SELECT a FROM AnimeModel a WHERE LOWER(a.titulo) LIKE LOWER(CONCAT('%', :nome, '%')) " +
            "ORDER BY CASE WHEN LOWER(a.titulo) LIKE LOWER(CONCAT(:nome, '%')) THEN 0 ELSE 1 END, " +
            "LENGTH(a.titulo) ASC")
    Page<AnimeModel> buscaCompleta(@Param("nome") String nome, Pageable pageable);

    Optional<AnimeModel> findByTituloIgnoreCase(String titulo);

    @Query("SELECT a FROM AnimeModel a ORDER BY CASE WHEN a.nota IS NULL THEN 0 ELSE 1 END DESC, a.nota DESC")
    Page<AnimeModel> buscarPopulares(Pageable pageable);
}
