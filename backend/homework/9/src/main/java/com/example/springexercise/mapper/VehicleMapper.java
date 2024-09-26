package com.example.springexercise.mapper;

import com.example.springexercise.Dto.VehicleDto;
import com.example.springexercise.model.Vehicle;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VehicleMapper {
    public static Vehicle getVehicleObject(VehicleDto vehicleDto){
       return new Vehicle(vehicleDto.getVehicleName(), vehicleDto.getVehiclePrice());
    }

    public static VehicleDto getVehicleDtoObject(Vehicle vehicle){
       return new VehicleDto(vehicle.getName(), vehicle.getPrice());
    }

}
