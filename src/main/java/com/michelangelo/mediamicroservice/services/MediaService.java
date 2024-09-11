package com.michelangelo.mediamicroservice.services;

import com.michelangelo.mediamicroservice.VO.MediaUser;
import com.michelangelo.mediamicroservice.entities.Media;
import com.michelangelo.mediamicroservice.exceptions.IllegalAccessException;
import com.michelangelo.mediamicroservice.exceptions.ResourceNotFoundException;
import com.michelangelo.mediamicroservice.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MediaService implements MediaServiceInterface{
    @Autowired
    MediaRepository mediaRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Media getMediaById(Long id,String username) {
        MediaUser user = restTemplate.getForObject("http://UserMicroservice/user/getuser/"+id, MediaUser.class);
        if (!user.getUserName().equals(username)){
            throw new IllegalAccessException(username);
        }
        Media mediaToReturn = mediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Media", "id", id));


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
