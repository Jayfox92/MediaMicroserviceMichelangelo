package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.entities.Album;
import com.michelangelo.mediamicroservice.entities.Artist;
import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.exceptions.ResourceNotFoundException;
import com.michelangelo.mediamicroservice.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArtistService implements ArtistServiceInterface{

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }


    @Override
    public List<Album> getAllAlbums(long id) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Artist", "id", id));
        return artist.getAlbums();
    }

    @Override
    public List<Media> getMediaByArtist(long id) {
        Artist artist = artistRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Artist","id",id));
        return artist.getCreatedMedia();
    }
}
