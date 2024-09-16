package com.example.SpringExercise3.mapper;

import com.example.SpringExercise3.Dto.VehicleDto;
import com.example.SpringExercise3.model.Vehicle;

public class VehicleMapper {
    public static Vehicle getVehicleObject(VehicleDto vehicleDto){
        Vehicle vehicle = new Vehicle(vehicleDto.getVehicleName(), vehicleDto.getVehiclePrice());
        return vehicle;
    }

    public static VehicleDto getVehicleDtoObject(Vehicle vehicle){
        VehicleDto vehicleDto = new VehicleDto(vehicle.getName(), vehicle.getPrice());
        return vehicleDto;
    }

}
