package com.tours.tourism.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tours.tourism.entities.Tour;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@Builder
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourDTO {

    private String id;

    @NotNull(message = "A tour must have a name")
    private String name;

    @NotNull(message = "A tour must have a duration")
    private Integer duration;

    @NotNull(message = "A tour must have a group size")
    private Integer maxGroupSize;

    @NotNull(message = "A tour must have a difficulty")
    private String difficulty;

    @NotNull(message = "A tour must have a price")
    private Double price;

    private Double ratingsAverage;

    private Integer ratingsQuantity;

    private Double priceDiscount;

    @NotNull(message = "A tour must have a summary")
    private String summary;

    private String description;

    @NotNull(message = "A tour must have a cover image")
    private String imageCover;

    private List<String> images;

    private LocalDateTime createdAt;

    private List<LocalDateTime> startDates;

    private String dummy;

    public TourDTO(){
        ratingsAverage = 4.5;
    }

    public Tour convertToEntity() {
        return Tour.builder()
                .name(name)
                .price(price)
                .ratingsAverage(ratingsAverage)
                .ratingsQuantity(ratingsQuantity)
                .difficulty(difficulty)
                .maxGroupSize(maxGroupSize)
                .duration(duration)
                .priceDiscount(priceDiscount)
                .summary(summary)
                .description(description)
                .imageCover(imageCover)
                .images(images)
                .createdAt(createdAt)
                .startDates(startDates)
                .build();
    }

//    public TourDTO(String id, String name, Integer duration, Integer maxGroupSize, String difficulty,
//                   Double price, Double ratingsAverage, Integer ratingsQuantity, Double priceDiscount,
//                   String summary, String description, String imageCover, List<String> images,
//                   LocalDateTime createdAt, List<LocalDateTime> startDates) {
//        //default
//        if (ratingsAverage == null) ratingsAverage = 4.5;
//        if (ratingsQuantity == null) ratingsQuantity = 0;
//        if(createdAt == null) createdAt = LocalDateTime.now();
//
//        this.id = id;
//        this.name = name;
//        this.duration = duration;
//        this.maxGroupSize = maxGroupSize;
//        this.difficulty = difficulty;
//        this.price = price;
//        this.ratingsAverage = ratingsAverage;
//        this.ratingsQuantity = ratingsQuantity;
//        this.priceDiscount = priceDiscount;
//        this.summary = nonNull(summary) ? summary.trim() : summary;
//        this.description = nonNull(description) ? description.trim() : description;
//        this.imageCover = imageCover;
//        this.images = images;
//        this.createdAt = createdAt;
//        this.startDates = startDates;
//    }

    @Builder
    public TourDTO(String id, String name, Integer duration, Integer maxGroupSize, String difficulty,
                   Double price, Double ratingsAverage, Integer ratingsQuantity, Double priceDiscount,
                   String summary, String description, String imageCover, List<String> images,
                   LocalDateTime createdAt, List<LocalDateTime> startDates, String dummy) {

        this.id = id;
        this.name = name;
        this.duration = duration;
        this.maxGroupSize = maxGroupSize;
        this.difficulty = difficulty;
        this.price = price;
        this.ratingsAverage = ratingsAverage;
        this.ratingsQuantity = ratingsQuantity;
        this.priceDiscount = priceDiscount;
        this.summary = summary;
        this.description = description;
        this.imageCover = imageCover;
        this.images = images;
        this.createdAt = createdAt;
        this.startDates = startDates;
    }
}
