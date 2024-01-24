package com.example.SpringExercise3.controller;

import com.example.SpringExercise3.Dto.VehicleDto;
import com.example.SpringExercise3.mapper.VehicleMapper;
import com.example.SpringExercise3.model.Vehicle;
import com.example.SpringExercise3.respository.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {

    @PostMapping("/vehicle")
    public ResponseEntity<String> addVehicle(@RequestBody VehicleDto vehicleDto){
                VehicleService.addVehicles(
                        VehicleMapper.getVehicleObject(vehicleDto));
        return ResponseEntity.ok("Vehicle Added successfully");
    }

    @GetMapping("/vehicle/{id}")
    public VehicleDto getVehicle(@PathVariable int id){
        return VehicleMapper.getVehicleDtoObject(
                VehicleService.getVehicles(id));
    }

    @PutMapping("/vehicle/update/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable int id, @RequestBody VehicleDto vehicleDto){
        VehicleService.updateVehicles( id, vehicleDto);
        return ResponseEntity.ok("Vehicle Updated successfully");
    }

    @DeleteMapping("/vehicle/delete/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int id) {
        VehicleService.deleteVehicles(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    @GetMapping("/vehicle/mostExpensive")
    public VehicleDto getExpensiveVehicle(){
        return VehicleService.mostExpensive();
    }

    @GetMapping("/vehicle/leastExpensive")
    public VehicleDto getLeastExpensiveVehicle(){
        return VehicleService.leastExpensive();
    }
}
