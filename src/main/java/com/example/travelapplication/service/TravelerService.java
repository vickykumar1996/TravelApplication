package com.example.travelapplication.service;

import com.example.travelapplication.payload.TravelerDto;

import java.util.List;

public interface TravelerService {


    TravelerDto createTraveler(TravelerDto travelerDto);

    List<TravelerDto> getAllTravelers();

    TravelerDto getTravelerById(Long id);

    TravelerDto updateTraveler(Long id, TravelerDto travelerDto);

    void deleteTraveler(Long id);

    List<TravelerDto> findByName(String name);
}
