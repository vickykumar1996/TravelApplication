package com.example.travelapplication.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;
    private String source;
    private String destination;
    private String date;


    @ManyToOne
    @JoinColumn(name = "traveler_id")
    private Traveler traveler;



}
