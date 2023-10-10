package com.sachin.hotelservice.util.mapper;

import com.sachin.hotelservice.dto.HotelDTO;
import com.sachin.hotelservice.entity.Hotel;

public interface Mapper {
    Hotel toHotel(HotelDTO hotelDTO);
    HotelDTO toHotelDto(Hotel hotel);
}
