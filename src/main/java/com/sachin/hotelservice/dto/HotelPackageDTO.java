package com.sachin.hotelservice.dto;

import com.sachin.hotelservice.entity.enums.HotelPackageRoomType;
import com.sachin.hotelservice.entity.enums.HotelPackageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelPackageDTO {
    private String hotelPackageId;
    private HotelPackageType hotelPackageType;
    private HotelPackageRoomType hotelPackageRoomType;
    private double hotelPackagePrice;
    private String hotelId;
}
