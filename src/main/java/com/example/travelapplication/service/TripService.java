package com.example.travelapplication.service;

import com.example.travelapplication.payload.TravelerDto;
import com.example.travelapplication.payload.TripDto;

import java.util.List;

public interface TripService {
    TripDto createTrip(TripDto tripDto);

    List<TripDto> getAllTrips();

    TripDto getTripById(Long tripId);

    TripDto updateTrip(Long tripId, TripDto tripDto);

    void deleteById(Long tripId);

}
