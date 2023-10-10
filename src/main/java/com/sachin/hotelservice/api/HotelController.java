package com.sachin.hotelservice.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sachin.hotelservice.dto.HotelDTO;
import com.sachin.hotelservice.dto.HotelPackageDTO;
import com.sachin.hotelservice.entity.enums.HotelCategory;
import com.sachin.hotelservice.exception.InValidDataException;
import com.sachin.hotelservice.service.HotelService;
import com.sachin.hotelservice.util.StandardResponse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.InputMismatchException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StandardResponse> createHotel(
            @RequestPart
            List<HotelPackageDTO> data,
            @RequestPart List<MultipartFile> hotelImagesRequest

    ) {
//        String hotelId = hotelService.createHotel(hotelDTO);
//        return new ResponseEntity<>(
//                new StandardResponse(HttpStatus.CREATED.value(), hotelId + " Created Hotel", hotelId),
//                HttpStatus.CREATED);

        System.out.println(data);
        System.out.println(hotelImagesRequest);
        return null;
    }

    @GetMapping(value = "/{hotelId}")
    public ResponseEntity<StandardResponse> getHotel(@PathVariable String hotelId) {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(), "OK", hotelService.getHotel(hotelId)),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getHotels() {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(), "OK", hotelService.getHotelDtos()),
                HttpStatus.OK);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<StandardResponse> deleteHotel(@PathVariable String hotelId) {
        return new ResponseEntity<>(
                new StandardResponse(),
                HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/{hotelId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StandardResponse> updateHotel(
            @Validated @ModelAttribute HotelDTO hotelDTO,
            @PathVariable String hotelId) {

        hotelService.update(hotelId, hotelDTO);
        return new ResponseEntity<>(
                new StandardResponse(),
                HttpStatus.NO_CONTENT);
    }

    @GetMapping("/category/{hotelCategory}")
    public ResponseEntity<StandardResponse> getHotelsWithCategory(@PathVariable String hotelCategory) {
        HotelCategory.getCategory(hotelCategory);

        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(), "OK", hotelService.getHotelDtos()),
                HttpStatus.OK);
    }

}

/*
    @RequestPart String hotel_name,


    @RequestPart HotelCategory hotelCategory,

    @RequestPart String hotelLocation,

    @RequestPart String hotelLocationCoordinates,

    @RequestPart String hotelEmail,

    @RequestPart String hotelContactNumber,

    @RequestPart Boolean isHotelPetsAllowed,

    @RequestPart Boolean isHotelCancellationCriteriaFree,

    @RequestPart double hotelCancellationCost,

    @RequestPart String hotelRemarks,

    @RequestPart List<MultipartFile> hotelImagesRequest;

    @RequestPart
    List<String> hotelImagesResponse;

    @RequestPart
    HotelPackageDTO data*/
