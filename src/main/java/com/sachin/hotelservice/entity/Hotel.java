package com.sachin.hotelservice.entity;

import com.sachin.hotelservice.entity.enums.HotelCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String hotel_id;

    @Column(nullable = false)
    private String hotel_name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HotelCategory hotelCategory;

    @Column(nullable = false, columnDefinition = "LongText")
    @Lob
    private String hotelLocationCoordinates;

    @Column(nullable = false)
    private String hotelLocation;

    @Email
    @Column(nullable = false)
    private String hotelEmail;

    @Column(nullable = false)
    private String hotelContactNumber;

    @Column(nullable = false)
    private Boolean isHotelPetsAllowed;

    @Column(nullable = false)
    private Boolean isHotelCancellationCriteriaFree;

    @Column
    private double hotelCancellationCost;

    @Column(nullable = false)
    private String hotelRemarks;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<HotelImage> hotelImages = new ArrayList<>();

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<HotelPackage> hotelPackageList = new ArrayList<>();
}
