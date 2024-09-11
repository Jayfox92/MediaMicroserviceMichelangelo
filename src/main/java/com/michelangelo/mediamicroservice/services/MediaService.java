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

@Service
public class MediaService implements MediaServiceInterface{
    @Autowired
    MediaRepository mediaRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Media getMediaById(Long id,String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("Kalle", "Kalle");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        //MediaUser user = restTemplate.getForObject("http://UserMicroservice/v2/user/getuser/"+id, MediaUser.class);
        MediaUser user = new MediaUser();
        try {
            ResponseEntity<MediaUser> response = restTemplate.exchange(
                    "http://UserMicroservice/v2/user/getuser/" + id,
                    HttpMethod.GET,
                    entity,
                    MediaUser.class
            );
            user = response.getBody();
            if (user == null || !user.getUserName().equals(username)) {
                throw new IllegalAccessException(username);
            }
        } catch (Exception e) {e.printStackTrace();}
        try {
            restTemplate.exchange(
                    "http://UserMicroservice/v2/streamhistory/increment/" + user.getId() + "/" + id,
                    HttpMethod.PUT,
                    entity,
                    Void.class
            );
        } catch (Exception e){
            e.printStackTrace();
        }
        //restTemplate.put("http://UserMicroservice/v2/streamhistory/increment/" + user.getId() + "/" + id, null);
        return mediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Media", "id", id));
    }

    /*
    Pre-security config/logging of media for user
    @Override
    public Media getMediaById(Long id) {
        return mediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Media", "id", id));
    }*/
}
