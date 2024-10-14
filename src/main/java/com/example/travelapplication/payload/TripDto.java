package com.example.travelapplication.payload;

import lombok.Data;

@Data
public class TripDto {
    private Long tripId;
    private String source;
    private String destination;
    private String date;
    private Long travelerId; // To associate with Traveler
}
