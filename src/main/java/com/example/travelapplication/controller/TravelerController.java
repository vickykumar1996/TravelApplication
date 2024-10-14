package com.example.travelapplication.controller;


import com.example.travelapplication.payload.TravelerDto;
import com.example.travelapplication.payload.TripDto;
import com.example.travelapplication.service.TravelerService;
import com.example.travelapplication.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/travelers")
public class TravelerController {

    @Autowired
    private TravelerService travelerService;

    @Autowired
    private TripService tripService;

    @PostMapping
    public ResponseEntity<TravelerDto> createTraveler(@Valid @RequestBody TravelerDto travelerDto) {
        TravelerDto createdTraveler = travelerService.createTraveler(travelerDto);
        return new ResponseEntity<>(createdTraveler, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TravelerDto>> getAllTravelers() {
        List<TravelerDto> travelers = travelerService.getAllTravelers();
        return new ResponseEntity<>(travelers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelerDto> getTravelerById(@PathVariable Long id) {
        TravelerDto travelerDto = travelerService.getTravelerById(id);
        return travelerDto != null ? new ResponseEntity<>(travelerDto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelerDto> updateTraveler(@PathVariable Long id, @Valid @RequestBody TravelerDto travelerDto) {
        TravelerDto updatedTraveler = travelerService.updateTraveler(id, travelerDto);
        return new ResponseEntity<>(updatedTraveler, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraveler(@PathVariable Long id) {
        travelerService.deleteTraveler(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Trip endpoints
    @PostMapping("/{travelerId}/trips")
    public ResponseEntity<TripDto> createTrip(@PathVariable Long travelerId, @Valid @RequestBody TripDto tripDto) {
        tripDto.setTravelerId(travelerId);
        TripDto createdTrip = tripService.createTrip(tripDto);
        return new ResponseEntity<>(createdTrip, HttpStatus.CREATED);
    }

    @GetMapping("/{travelerId}/trips")
    public ResponseEntity<List<TripDto>> getAllTrips(@PathVariable Long travelerId) {
        List<TripDto> trips = tripService.getAllTrips().stream()
                .filter(trip -> trip.getTravelerId().equals(travelerId))
                .collect(Collectors.toList());
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TravelerDto>> findByName(@RequestParam String name) {
        List<TravelerDto> travelers = travelerService.findByName(name);
        return new ResponseEntity<>(travelers, HttpStatus.OK);
    }
}

