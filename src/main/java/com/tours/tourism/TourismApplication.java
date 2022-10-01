package com.tours.tourism;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tours.tourism.dto.TourDTO;
import com.tours.tourism.entities.Tour;
import com.tours.tourism.service.TourService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class TourismApplication {

    public static void main(String[] args) {
        SpringApplication.run(TourismApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(TourService tourService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            //fix for deserializing LocalDateTime
            mapper.findAndRegisterModules();

            TypeReference<List<TourDTO>> typeReference = new TypeReference<List<TourDTO>>() {};
            InputStream inputStream = TypeReference.class
                    .getResourceAsStream("/static/dev-data/data/tours-simple.json");

            List<TourDTO> tours = mapper.readValue(inputStream, typeReference);

            //clear DB
            tourService.deleteAll();
            //save
            tours.forEach(tourService::saveTour);
            System.out.println("Users saved successfully");
        };
    }
}
