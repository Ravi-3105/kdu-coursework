package com.caching.service;

import com.caching.model.Address;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Reverse-Geocoding Service class asks for address
 */
@Service
public class ReverseGeocodingService {
    @Cacheable(value = "reverse-geocoding" , key = "{#latitude,#longitude}")
    public Address getAddress(double latitude, double longitude)  {
        return new AddressResponseService().getAddress(latitude,longitude);
    }
}
