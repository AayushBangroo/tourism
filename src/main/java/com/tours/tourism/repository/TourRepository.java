package com.tours.tourism.repository;

import com.tours.tourism.entities.Tour;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TourRepository extends MongoRepository<Tour, String> {

}
