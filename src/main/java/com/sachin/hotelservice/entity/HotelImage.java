package com.sachin.hotelservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class HotelImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String hotelImgId;

    @Column(nullable = false, columnDefinition = "LongText")
    @Lob
    private String hotelImgValue;
    @ManyToOne
    private Hotel hotel;
}
