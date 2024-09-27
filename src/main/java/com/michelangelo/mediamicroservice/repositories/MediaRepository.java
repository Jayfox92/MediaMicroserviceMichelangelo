package com.michelangelo.mediamicroservice.repositories;

import com.michelangelo.mediamicroservice.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    List<Media> findByGenres_IdAndTypeOfMedia_Type(Long genreId, String mediaType);

    List<Media> findByTypeOfMedia_Type(String typeOfMedia);
}
