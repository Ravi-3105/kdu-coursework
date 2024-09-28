package com.caching.service;

import com.caching.model.Coordinate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * Geocoding Service class asks for co-ordinates
 */
@Service
public class GeocodingService {

    @Cacheable(value = "geocoding", key = "#address")
    public Coordinate getCoordinate(String address){
        return new CoordinateResponseService().getCoordinate(address);
    }
}
