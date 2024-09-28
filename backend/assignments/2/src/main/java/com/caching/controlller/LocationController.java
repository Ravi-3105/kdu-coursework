package com.caching.controlller;

import com.caching.dto.AddressDto;
import com.caching.dto.CoordinateDto;
import com.caching.mapper.Mapper;
import com.caching.service.ReverseGeocodingService;
import com.caching.service.GeocodingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationController {

    private final Logger logger = LoggerFactory.getLogger(LocationController.class);
    @GetMapping("/geocoding")
    public ResponseEntity<CoordinateDto> getCoordinate(@RequestParam("address") String address){
        logger.info("Co-ordinates Required");
        return new ResponseEntity<>(new Mapper().mapCoordianteDto(new GeocodingService()
                .getCoordinate(address)), HttpStatus.OK);
    }
    @GetMapping("/reverse-geocoding")
    public ResponseEntity<AddressDto> getAddress(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude){
        logger.info("Address Required");
        return new ResponseEntity<>(new Mapper().mapAddressDto(new ReverseGeocodingService().
                getAddress(latitude,longitude)),HttpStatus.OK);
    }
}
