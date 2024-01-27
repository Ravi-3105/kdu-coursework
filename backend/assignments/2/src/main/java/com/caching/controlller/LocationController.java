package com.caching.controlller;

import com.caching.dto.AddressDto;
import com.caching.dto.CoordinateDto;
import com.caching.mapper.Mapper;
import com.caching.model.Address;
import com.caching.model.Coordinate;
import com.caching.service.CoordinateResponseService;
import com.caching.service.ReverseGeocodingService;
import com.caching.service.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationController {

    @GetMapping("/geocoding")
    public ResponseEntity<CoordinateDto> getCoordinate(@RequestParam("address") String address){
        return new ResponseEntity<CoordinateDto>(new Mapper().mapCoordianteDto(new GeocodingService()
                .getCoordinate(address)), HttpStatus.OK);
    }
    @GetMapping("/reverse-geocoding")
    public ResponseEntity<AddressDto> getAddress(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) throws Exception {

        return new ResponseEntity<AddressDto>(new Mapper().mapAddressDto(new ReverseGeocodingService().
                getAddress(latitude,longitude)),HttpStatus.OK);
    }
}
