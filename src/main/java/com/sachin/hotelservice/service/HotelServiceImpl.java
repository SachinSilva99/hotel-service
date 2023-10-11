package com.sachin.hotelservice.service;

import com.sachin.hotelservice.dto.HotelDTO;
import com.sachin.hotelservice.entity.Hotel;
import com.sachin.hotelservice.entity.HotelImage;
import com.sachin.hotelservice.entity.HotelPackage;
import com.sachin.hotelservice.entity.enums.HotelCategory;
import com.sachin.hotelservice.exception.NotFoundException;
import com.sachin.hotelservice.repo.HotelImageRepo;
import com.sachin.hotelservice.repo.HotelPackageRepo;
import com.sachin.hotelservice.repo.HotelRepo;
import com.sachin.hotelservice.util.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepo hotelRepo;
    private final HotelImageRepo hotelImageRepo;
    private final HotelPackageRepo hotelPackageRepo;
    private final Mapper mapper;

    @Override
    public String createHotel(HotelDTO hotelDTO) {

        Hotel hotel = mapper.toHotel(hotelDTO);
        String hotel_id = hotelRepo.save(hotel).getHotel_id();

        hotelDTO.getHotelImagesStrings().forEach(imageString -> {
            HotelImage hotelImage = HotelImage.builder()
                    .hotelImgValue(imageString)
                    .hotel(hotel).build();
            hotelImageRepo.save(hotelImage);
        });

        hotelDTO.getHotelPackageDTOS().forEach(hotelPackageDTO -> {
            HotelPackage hotelPackage = mapper.toHotelPackage(hotelPackageDTO);
            hotelPackage.setHotel(hotel);
            hotelPackageRepo.save(hotelPackage);
        });
        return hotel_id;
    }

    @Override
    public HotelDTO getHotel(String hotelId) {
        Optional<Hotel> byId = hotelRepo.findById(hotelId);
        if (byId.isEmpty()) {
            throw new NotFoundException("hotel id : " + hotelId + " not found");
        }
        Hotel hotel = byId.get();
        return getHotelDTO(hotel);
    }


    @Override
    public void update(String hotelId, HotelDTO hotelDTO) {
        Optional<Hotel> byId = hotelRepo.findById(hotelId);
        if (byId.isEmpty()) {
            throw new NotFoundException("hotel id : " + hotelId + " not found");
        }
        Hotel hotel = byId.get();
        hotel.setHotel_name(hotelDTO.getHotel_name());
        hotel.setHotelCategory(hotelDTO.getHotelCategory());
        hotel.setHotelLocationCoordinates(hotelDTO.getHotelLocationCoordinates());
        hotel.setHotelLocation(hotelDTO.getHotelLocation());
        hotel.setHotelEmail(hotelDTO.getHotelEmail());
        hotel.setHotelContactNumber(hotelDTO.getHotelContactNumber());
        hotel.setIsHotelPetsAllowed(hotelDTO.getIsHotelPetsAllowed());
        hotel.setIsHotelCancellationCriteriaFree(hotelDTO.getIsHotelCancellationCriteriaFree());
        hotel.setHotelCancellationCost(hotelDTO.getHotelCancellationCost());
        hotel.setHotelRemarks(hotelDTO.getHotelRemarks());

        hotelDTO.getHotelImagesStrings().forEach(imageString -> {
            HotelImage hotelImage = HotelImage.builder()
                    .hotelImgValue(imageString)
                    .hotel(hotel).build();
            hotelImageRepo.save(hotelImage);
        });
        hotelDTO.getHotelPackageDTOS().forEach(hotelPackageDTO -> {
            HotelPackage hotelPackage = mapper.toHotelPackage(hotelPackageDTO);
            hotelPackage.setHotel(hotel);
            hotelPackageRepo.save(hotelPackage);
        });

        hotelRepo.save(hotel);

    }

    @Override
    public void delete(String hotelId) {
        Optional<Hotel> byId = hotelRepo.findById(hotelId);
        if (byId.isEmpty()) {
            throw new NotFoundException("hotel id : " + hotelId + " not found");
        }
        String hotel_id = byId.get().getHotel_id();
        hotelRepo.deleteById(hotel_id);
    }

    @Override
    public List<HotelDTO> getHotelDtos() {
        return hotelRepo.findAll().stream().map(this::getHotelDTO).toList();
    }

    @Override
    public List<HotelDTO> findHotelsWithCategory(HotelCategory hotelCategory) {
        return hotelRepo.findAllByHotelCategory(hotelCategory).stream().map(this::getHotelDTO).toList();
    }

    @Override
    public List<HotelDTO> findHotelsWithIsPetsAllowed(boolean isPetsAllowed) {
        return hotelRepo.findAllByIsHotelPetsAllowed(isPetsAllowed).stream().map(this::getHotelDTO).toList();
    }

    @Override
    public List<HotelDTO> findHotelsWithIsCancellationCriteria(boolean isCancellationCriteriaFree) {
        return hotelRepo
                .findAllByIsHotelCancellationCriteriaFree(isCancellationCriteriaFree)
                .stream()
                .map(this::getHotelDTO)
                .toList();
    }

    @Override
    public List<HotelDTO> findHotelsByHotelCategoryPetsAllowedCancellationCriteria(
            HotelCategory hotelCategory, Boolean isHotelCancellationCriteria, Boolean isHotelPetsAllowed) {
        return hotelRepo
                .findAllByHotelCategoryAndIsHotelCancellationCriteriaFreeAndIsHotelPetsAllowed(
                        hotelCategory,
                        isHotelCancellationCriteria,
                        isHotelPetsAllowed).stream().map(this::getHotelDTO).toList();
    }

    private HotelDTO getHotelDTO(Hotel hotel) {
        HotelDTO hotelDto = mapper.toHotelDto(hotel);

        List<String> list = hotel.getHotelImages().stream().map(HotelImage::getHotelImgValue).toList();
        hotelDto.setHotelImagesStrings(list);
        return hotelDto;
    }
}
