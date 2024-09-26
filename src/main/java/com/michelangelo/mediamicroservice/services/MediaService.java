package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.VO.MediaUser;
import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.exceptions.ResourceNotFoundException;
import com.michelangelo.mediamicroservice.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class MediaService implements MediaServiceInterface {
    @Autowired
    MediaRepository mediaRepository;
    @Autowired
    private RestTemplate restTemplate;


    // Hämta media med media id och user id samt spara spelningen av media
    @Override
    public Media getMedia(Long mediaId, Long userId) {
        Media mediaToReturn = mediaRepository.findById(mediaId)
                .orElseThrow(() -> new ResourceNotFoundException("Media", "id", mediaId));

        try{
            MediaUser user = restTemplate.getForObject("http://UserMicroservice/user/mediauser/getuser/" + userId, MediaUser.class);
            if (user == null) throw new ResourceNotFoundException("MediaUser", "id", userId);
            restTemplate.put("http://UserMicroservice/user/streamhistory/increment/" + userId + "/" + mediaId, Void.class);
        }catch(RestClientException e){ // Olämpligt tillstånd, det går inte att nå mikrotjänsten // IllegalStateException | ResourceAccessException e
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "UserMicroservice: " + e.getMessage(), e);
        }

        return mediaToReturn;
    }

    // Hämta media med id utan att spara spelningen av media
    @Override
    public Media getMediaById(Long id) {
        return mediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Media", "id", id));
    }

    // Hämta media baserat på genreId
    @Override
    public List<Media> findMediaByGenreId(Long genreId) {
        return mediaRepository.findByGenres_Id(genreId);
    }
    @Override
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }
}
