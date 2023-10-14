package com.sachin.hotelservice.api;

import com.sachin.hotelservice.dto.HotelDTO;
import com.sachin.hotelservice.service.HotelService;
import com.sachin.hotelservice.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/get", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin("**")
public class GetHotelForCustomersController {
    private final HotelService hotelService;
    @GetMapping(value = "/{hotelId}")
    public ResponseEntity<StandardResponse<HotelDTO>> getHotel(@PathVariable String hotelId) {
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.OK.value(), "OK", hotelService.getHotel(hotelId)),
                HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<StandardResponse<List<HotelDTO>>> getALlHotel() {
        return new ResponseEntity<>(
                new StandardResponse<>(HttpStatus.OK.value(), "OK", hotelService.getHotelDtos()),
                HttpStatus.OK);
    }
}
