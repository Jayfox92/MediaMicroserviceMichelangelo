package com.michelangelo.mediamicroservice.repositories;

import com.michelangelo.mediamicroservice.entities.Album;
import com.michelangelo.mediamicroservice.entities.Artist;
import com.michelangelo.mediamicroservice.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    @Query("SELECT m FROM Media m JOIN m.artists a WHERE a.id = :artistId")
    List<Media> findAllMediaByArtistId(@Param("artistId") long artistId);

    @Query("SELECT a FROM Album a JOIN a.artists ar WHERE ar.id = :artistId")
    List<Album> findAlbumsById(@Param ("artistId") Long artistId);

}
