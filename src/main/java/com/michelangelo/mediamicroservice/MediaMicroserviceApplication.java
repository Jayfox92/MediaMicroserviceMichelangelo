package com.michelangelo.mediamicroservice;

import com.michelangelo.mediamicroservice.exceptions.UserServiceResponseErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MediaMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediaMicroserviceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new UserServiceResponseErrorHandler());
        return restTemplate;
    }


}
