package com.tours.tourism.service;

import com.tours.tourism.dto.MonthlyPlan;
import com.tours.tourism.dto.TourDTO;
import com.tours.tourism.dto.TourStats;
import com.tours.tourism.entities.Tour;
import com.tours.tourism.repository.TourCustomRepository;
import com.tours.tourism.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourCustomRepository tourCustomRepository;

    public List<TourDTO> findAll(Map<String, String> params) {

        return tourCustomRepository.findAllByParams(params).stream()
                .map(Tour::convertToDTO)
                .collect(Collectors.toList());
    }

    public TourDTO getTour(String id) {

        Tour tour = tourRepository.findById(id).orElseThrow();
        return tour.convertToDTO();
    }

    public List<TourStats> getTourStats(){

        return tourCustomRepository.getTourStats();
    }

    public List<MonthlyPlan> getMonthlyPlan(int year){

        return tourCustomRepository.getMonthlyPlan(year);
    }

    public TourDTO saveTour(TourDTO dto) {

        return tourRepository.insert(dto.convertToEntity())
                .convertToDTO();
    }

    //TODO
    public TourDTO updateTour(String id, TourDTO dto) {

        Tour tour = tourRepository.findById(id).orElseThrow();
        tour.setPrice(dto.getPrice() != null ? dto.getPrice() : tour.getPrice());
        tour.setRatingsAverage(dto.getRatingsAverage() != null ? dto.getRatingsAverage() : tour.getRatingsAverage());
        tour.setRatingsQuantity(dto.getRatingsQuantity() != null ? dto.getRatingsQuantity() : tour.getRatingsQuantity());
        tour.setName(dto.getName() != null ? dto.getName() : tour.getName());

        return tourRepository.save(tour).convertToDTO();
    }

    public void deleteTour(String id) {

        Tour tour = tourRepository.findById(id).orElseThrow();

        tourRepository.deleteById(id);
    }

    public void deleteAll() {
        tourRepository.deleteAll();
    }
}
