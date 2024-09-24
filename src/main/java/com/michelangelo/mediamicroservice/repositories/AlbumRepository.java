package com.michelangelo.mediamicroservice.repositories;

import com.michelangelo.mediamicroservice.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

}
