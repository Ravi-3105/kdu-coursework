package com.example.springexercise.service;

import com.example.springexercise.Dto.VehicleDto;
import com.example.springexercise.mapper.VehicleMapper;
import com.example.springexercise.model.Vehicle;
import com.example.springexercise.respository.Inventry;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VehicleService {


    public static int addVehicles(Vehicle vehicle){
        Inventry.vehicles.add(vehicle);
        return Inventry.vehicles.size()-1;
    }

    public static Vehicle getVehicles(int id){
        return Inventry.vehicles.get(id);
    }

    public static void updateVehicles(int id, VehicleDto vehicleDto){
        Vehicle vehicle = VehicleMapper.getVehicleObject(vehicleDto);
        Inventry.vehicles.get(id).setName(vehicle.getName());
        Inventry.vehicles.get(id).setPrice(vehicle.getPrice());
    }

    public static void deleteVehicles(int id){
        Inventry.vehicles.remove(id);
    }

    public static VehicleDto leastExpensive(){
        return VehicleMapper.getVehicleDtoObject(Inventry.leastExpensive());
    }

    public static VehicleDto mostExpensive(){
        return VehicleMapper.getVehicleDtoObject(Inventry.mostExpensive());
    }
}
