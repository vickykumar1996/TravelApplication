package com.example.travelapplication.service;

import com.example.travelapplication.entity.Traveler;
import com.example.travelapplication.payload.TravelerDto;
import com.example.travelapplication.repository.TravelerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelerServiceImpl implements TravelerService {


    @Autowired
    private TravelerRepository travelerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TravelerDto createTraveler(TravelerDto travelerDto) {
        Traveler traveler = modelMapper.map(travelerDto, Traveler.class);
        traveler = travelerRepository.save(traveler);
        return modelMapper.map(traveler, TravelerDto.class);
    }

    @Override
    public List<TravelerDto> getAllTravelers() {
        return travelerRepository.findAll().stream()
                .map(traveler -> modelMapper.map(traveler, TravelerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TravelerDto getTravelerById(Long id) {
        Traveler traveler = travelerRepository.findById(id).orElse(null);
        return modelMapper.map(traveler, TravelerDto.class);
    }

    @Override
    public TravelerDto updateTraveler(Long id, TravelerDto travelerDto) {
        Traveler traveler = travelerRepository.findById(id).orElse(null);
        if (traveler != null) {
            traveler.setName(travelerDto.getName());
            traveler.setEmail(travelerDto.getEmail());
            traveler = travelerRepository.save(traveler);
        }
        return modelMapper.map(traveler, TravelerDto.class);
    }

    @Override
    public void deleteTraveler(Long id) {
        travelerRepository.deleteById(id);
    }

    @Override
    public List<TravelerDto> findByName(String name) {
        return travelerRepository.findByName(name).stream()
                .map(traveler -> modelMapper.map(traveler, TravelerDto.class))
                .collect(Collectors.toList());
    }
}
