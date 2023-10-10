package com.sachin.hotelservice.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sachin.hotelservice.entity.HotelPackage;
import com.sachin.hotelservice.entity.enums.HotelCategory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDTO {

    @JsonIgnore
    private String hotel_id;

    @NotBlank
    private String hotel_name;

    @NotNull
    private HotelCategory hotelCategory;

    @NotBlank
    private String hotelLocation;

    @NotBlank
    private String hotelLocationCoordinates;

    @Email
    private String hotelEmail;

    @NotBlank
    private String hotelContactNumber;

    @NotNull
    private Boolean isHotelPetsAllowed;

    @NotNull
    private Boolean isHotelCancellationCriteriaFree;

    private double hotelCancellationCost;

    @NotBlank
    private String hotelRemarks;

    @NotEmpty
    private List<MultipartFile> hotelImagesRequest;

    private List<String> hotelImagesResponse;

    private HotelPackageDTO hotelPackageDTOS;

}
