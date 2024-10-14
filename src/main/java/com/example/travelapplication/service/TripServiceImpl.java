package com.example.travelapplication.service;

import com.example.travelapplication.entity.Traveler;
import com.example.travelapplication.entity.Trip;
import com.example.travelapplication.payload.TravelerDto;
import com.example.travelapplication.payload.TripDto;
import com.example.travelapplication.repository.TravelerRepository;
import com.example.travelapplication.repository.TripRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TravelerRepository travelerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TripDto createTrip(TripDto tripDto) {
        Trip trip = modelMapper.map(tripDto, Trip.class);
        Traveler traveler = travelerRepository.findById(tripDto.getTravelerId()).orElse(null);
        if (traveler != null) {
            trip.setTraveler(traveler);
            trip = tripRepository.save(trip);
        }
        return modelMapper.map(trip, TripDto.class);
    }

    @Override
    public List<TripDto> getAllTrips() {
        return tripRepository.findAll().stream()
                .map(trip -> modelMapper.map(trip, TripDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TripDto getTripById(Long id) {
        Trip trip = tripRepository.findById(id).orElse(null);
        return modelMapper.map(trip, TripDto.class);
    }

    @Override
    public TripDto updateTrip(Long id, TripDto tripDto) {
        Trip trip = tripRepository.findById(id).orElse(null);
        if (trip != null) {
            trip.setDestination(tripDto.getDestination());
            trip.setDate(tripDto.getDate());
            Traveler traveler = travelerRepository.findById(tripDto.getTravelerId()).orElse(null);
            trip.setTraveler(traveler);
            trip = tripRepository.save(trip);
        }
        return modelMapper.map(trip, TripDto.class);
    }

    @Override
    public void deleteById(Long tripId) {
        tripRepository.deleteById(tripId);

    }


}

