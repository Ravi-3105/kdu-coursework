package com.caching.service;

import com.caching.model.Address;
import com.caching.repository.BackendAPI;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class ReverseGeocodingService {
    @Cacheable(value = "reverse-geocoding" , key = "{#latitude,#longitude}")
    public Address getAddress(double latitude, double longitude)  {
        return new AddressResponseService().getAddress(latitude,longitude);
    }
}
