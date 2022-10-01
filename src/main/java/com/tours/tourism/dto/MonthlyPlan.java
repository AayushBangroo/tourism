package com.tours.tourism.dto;

import lombok.Data;

import java.util.List;

@Data
public class MonthlyPlan {
    private int month;
    private int numTourStarts;
    List<String> tour;
}
