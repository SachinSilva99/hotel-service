package com.sachin.hotelservice.api;

import com.sachin.hotelservice.dto.HotelDTO;
import com.sachin.hotelservice.service.HotelService;
import com.sachin.hotelservice.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin("**")
public class GetAllHotelsController {

    private final HotelService hotelService;
    @GetMapping
    public ResponseEntity<StandardResponse<List<HotelDTO>>> getAllHotelsByNoUser() {
        System.out.println(hotelService.getHotelDtos());
        return new ResponseEntity<>(new StandardResponse<>(
                200,
                "OK",
                hotelService.getHotelDtos()
        ), HttpStatus.OK);
    }
}
