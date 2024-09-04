package com.michelangelo.mediamicroservice.repositories;

import com.michelangelo.mediamicroservice.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
