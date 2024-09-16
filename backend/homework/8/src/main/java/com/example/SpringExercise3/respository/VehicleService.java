package com.example.SpringExercise3.respository;

import com.example.SpringExercise3.Dto.VehicleDto;
import com.example.SpringExercise3.mapper.VehicleMapper;
import com.example.SpringExercise3.model.Vehicle;
import org.springframework.web.bind.annotation.PathVariable;

public class VehicleService {

    public  static void addVehicles(Vehicle vehicle){
        Inventry.vehicles.add(vehicle);
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
