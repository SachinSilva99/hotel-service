package com.sachin.hotelservice.util.mapper;

import com.sachin.hotelservice.dto.HotelDTO;
import com.sachin.hotelservice.dto.HotelImageDTO;
import com.sachin.hotelservice.dto.HotelPackageDTO;
import com.sachin.hotelservice.entity.Hotel;
import com.sachin.hotelservice.entity.HotelImage;
import com.sachin.hotelservice.entity.HotelPackage;
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

    @Override
    public HotelPackageDTO toHotelPackageDto(HotelPackage hotelPackage) {
        return mapper.map(hotelPackage, HotelPackageDTO.class);
    }

    @Override
    public HotelPackage toHotelPackage(HotelPackageDTO hotelPackageDTO) {
        return mapper.map(hotelPackageDTO, HotelPackage.class);
    }

    @Override
    public HotelImageDTO toHotelImageDto(HotelImage hotelImage) {
        return mapper.map(hotelImage, HotelImageDTO.class);
    }

    @Override
    public HotelImage toHotelImage(HotelImageDTO hotelImageDTO) {
        return mapper.map(hotelImageDTO, HotelImage.class);
    }
}
