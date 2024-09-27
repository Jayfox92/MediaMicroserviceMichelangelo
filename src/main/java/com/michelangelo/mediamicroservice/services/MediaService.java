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


    @Override
    public Media getMedia(Long mediaId, Long userId) {
        Media mediaToReturn = mediaRepository.findById(mediaId).orElseThrow(() -> new ResourceNotFoundException("Media", "id", mediaId));

        try {
            MediaUser user = restTemplate.getForObject("http://UserMicroservice/user/mediauser/getuser/" + userId, MediaUser.class);
            if (user == null) throw new ResourceNotFoundException("MediaUser", "id", userId);
            restTemplate.put("http://UserMicroservice/user/streamhistory/increment/" + userId + "/" + mediaId, Void.class);
        } catch (RestClientException |
                 IllegalStateException e) { // Olämpligt tillstånd, det går inte att nå mikrotjänsten // IllegalStateException | ResourceAccessException e
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "UserMicroservice: " + e.getMessage(), e);
        }

        return mediaToReturn;
    }


    @Override
    public Media getMediaById(Long id) {
        return mediaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Media", "id", id));
    }


    @Override
    public List<Media> findMediaByGenreId(Long genreId, String mediaType) {
        return mediaRepository.findByGenres_IdAndTypeOfMedia_Type(genreId, mediaType);
    }

    @Override
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    @Override
    public List<Media> getAllMediaByType(String mediaType) {
        return mediaRepository.findByTypeOfMedia_Type(mediaType);
    }

}
