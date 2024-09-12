package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.VO.MediaUser;
import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.exceptions.IllegalAccessException;
import com.michelangelo.mediamicroservice.exceptions.ResourceNotFoundException;
import com.michelangelo.mediamicroservice.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class MediaService implements MediaServiceInterface{
    @Autowired
    MediaRepository mediaRepository;
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Media getMediaById(Long mediaId,Long userId) {
        Media mediaToReturn = mediaRepository.findById(mediaId)
                .orElseThrow(() -> new ResourceNotFoundException("Media", "id", mediaId));
        MediaUser user = restTemplate.getForObject("http://UserMicroservice/v2/user/getuser/"+userId, MediaUser.class);
        if (user == null) throw new ResourceNotFoundException("MediaUser", "id", userId);
        restTemplate.put("http://UserMicroservice/v2/streamhistory/increment/" + user.getId() + "/" + mediaId, Void.class);
        return mediaToReturn;
    }

    /*
    Pre-security config/logging of media for user
    @Override
    public Media getMediaById(Long id) {
        return mediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Media", "id", id));
    }*/
}
