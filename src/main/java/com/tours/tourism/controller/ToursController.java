package com.tours.tourism.controller;

import com.tours.tourism.dto.TourDTO;
import com.tours.tourism.service.TourService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/tours")
public class ToursController {

    @Autowired
    private TourService tourService;

    @GetMapping
    public ResponseEntity<Object> getAllTours(@RequestParam Map<String, String> params) {

        return new ResponseEntity<>(buildSuccessResponse(tourService.findAll(params)), HttpStatus.OK);
    }

    @GetMapping("/top-5-cheap")
    public ResponseEntity<Object> getTop5CheapTours() {
        Map<String, String> params = new HashMap<>();
        params.put("limit", "5");
        params.put("sort", "-ratingsAverage,price");
        params.put("fields", "name,price,ratingsAverage,summary,difficulty");

        return new ResponseEntity<>(buildSuccessResponse(tourService.findAll(params)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTour(@PathVariable String id) {

        return new ResponseEntity<>(buildSuccessResponse(tourService.getTour(id)), HttpStatus.OK);
    }

    @GetMapping("/tour-stats")
    public ResponseEntity<Object> getTourStats(){

        return new ResponseEntity<>(buildSuccessResponse(tourService.getTourStats()), HttpStatus.OK);
    }

    @GetMapping("/monthly-plan/{year}")
    public ResponseEntity<Object> getTourStats(@PathVariable int year){

        return new ResponseEntity<>(buildSuccessResponse(tourService.getMonthlyPlan(year)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createTour(@RequestBody @Valid TourDTO dto) {

        TourDTO newTour = tourService.saveTour(dto);

        return new ResponseEntity<>(buildSuccessResponse(newTour), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTour(@PathVariable String id, @RequestBody TourDTO dto) {

        TourDTO updatedTour = tourService.updateTour(id, dto);

        return new ResponseEntity<>(buildSuccessResponse(updatedTour), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTour(@PathVariable String id) {

        tourService.deleteTour(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    private Map<String, Object> buildSuccessResponse(Object obj) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "success");
        jsonObject.put("data", new JSONObject().put("tours", obj));

        return jsonObject.toMap();
    }
}
