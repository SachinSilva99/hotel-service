package com.sachin.hotelservice.util.mapper;

import com.sachin.hotelservice.dto.HotelDTO;
import com.sachin.hotelservice.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperImpl implements Mapper {
    private final ModelMapper mapper;

    @Override
    public Hotel toHotel(HotelDTO hotelDTO) {
        return mapper.map(hotelDTO, Hotel.class);
    }

    @Override
    public HotelDTO toHotelDto(Hotel hotel) {
        return mapper.map(hotel, HotelDTO.class);
    }
}
