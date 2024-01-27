package com.caching.mapper;

import com.caching.dto.AddressDto;
import com.caching.dto.CoordinateDto;
import com.caching.model.Address;
import com.caching.model.Coordinate;

public class Mapper {
    public CoordinateDto mapCoordianteDto(Coordinate coordinate){
        return new CoordinateDto(coordinate.getLatitude(), coordinate.getLongitude());
    }
    public AddressDto mapAddressDto(Address address){
        return new AddressDto(address.getAddress());
    }
}
