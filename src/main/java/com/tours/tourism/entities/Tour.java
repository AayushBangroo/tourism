package com.tours.tourism.entities;

import com.tours.tourism.dto.TourDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("tours")
@Data
@Builder
@AllArgsConstructor
@EnableMongoAuditing
public class Tour {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private Integer duration;

    private Integer maxGroupSize;

    private String difficulty;

    private Double price;

    private Double ratingsAverage;

    private Integer ratingsQuantity;

    private Double priceDiscount;

    private String summary;

    private String description;

    private String imageCover;

    private List<String> images;

    private LocalDateTime createdAt;

    private List<LocalDateTime> startDates;

    public Tour() {
    }

    public TourDTO convertToDTO() {
        return TourDTO.builder()
                .id(id)
                .name(name)
                .price(price)
                .ratingsAverage(ratingsAverage)
                .ratingsQuantity(ratingsQuantity)
                .duration(duration)
                .difficulty(difficulty)
                .maxGroupSize(maxGroupSize)
                .priceDiscount(priceDiscount)
                .summary(summary)
                .description(description)
                .imageCover(imageCover)
                .images(images)
                .createdAt(createdAt)
                .startDates(startDates)
                .dummy("")
                .build();
    }
}
