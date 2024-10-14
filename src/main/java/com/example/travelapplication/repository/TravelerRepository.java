package com.example.travelapplication.repository;

import com.example.travelapplication.entity.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelerRepository extends JpaRepository<Traveler,Long> {
    List<Traveler> findByName(String name);
}
