package com.tours.tourism.dto;

import lombok.Data;

@Data
public class TourStats {

    private int numTours;
    private int numRatings;
    private Double avgRating;
    private Double avgPrice;
    private Double minPrice;
    private Double maxPrice;
    private String _id;
}
