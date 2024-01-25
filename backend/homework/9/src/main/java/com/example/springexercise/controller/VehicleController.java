package com.example.springexercise.controller;

import com.example.springexercise.Dto.VehicleDto;
import com.example.springexercise.exception.custom.IndexNotFound;
import com.example.springexercise.mapper.VehicleMapper;
import com.example.springexercise.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {

    private final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @PostMapping("/vehicle")
    public ResponseEntity<String> addVehicle(@RequestBody VehicleDto vehicleDto){
             int  id =  VehicleService.addVehicles(
                        VehicleMapper.getVehicleObject(vehicleDto));
             logger.error("Successful Addition");
             logger.info("At index - ".concat(String.valueOf(id)));
        return ResponseEntity.ok("Vehicle Added successfully at index- ".concat(String.valueOf(id)));
    }

    @GetMapping("/vehicle/{id}")
    public VehicleDto getVehicle(@PathVariable int id){
        VehicleDto vehicleDto = null;
        try {
            vehicleDto = VehicleMapper.getVehicleDtoObject(
                    VehicleService.getVehicles(id));
            return vehicleDto;
        }catch (IndexOutOfBoundsException e){
            logger.debug("Index not found");
            throw new IndexNotFound("Index Not Found at- ".concat(String.valueOf(id)));
        }
    }

    /**
     * Prodution update mapping
     * @param id
     * @param vehicleDto
     * @return
     */
    @Profile("prod")
    @PutMapping("/vehicle/update/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable int id, @RequestBody VehicleDto vehicleDto){
        try {
            VehicleService.updateVehicles(id, vehicleDto);
            return ResponseEntity.ok("Vehicle Updated successfully at- ".concat(String.valueOf(id)));
        }catch (IndexOutOfBoundsException e){
            logger.debug("Index not found at update");
            throw new IndexNotFound("Index Not Found at- ".concat(String.valueOf(id)));
        }
    }

    /**
     * production delete mapping
     * @param id
     * @return
     */
    @Profile("prod")
    @DeleteMapping("/vehicle/delete/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int id) {
        try {
            VehicleService.deleteVehicles(id);
            return ResponseEntity.ok("Vehicle deleted successfully");
        }catch (IndexOutOfBoundsException e){
            logger.debug("Index not found at delete");
            throw new IndexNotFound("Index Not Found at- ".concat(String.valueOf(id)));
        }
    }

    /**
     * developer update mapping
     * @param id
     * @param vehicleDto
     * @return
     */
    @Profile("dev")
    @PutMapping("/vehicleUpdate/{id}")
    public ResponseEntity<String> updateVeDevhicle(@PathVariable int id, @RequestBody VehicleDto vehicleDto){
        try {
            VehicleService.updateVehicles(id, vehicleDto);
            return ResponseEntity.ok("Vehicle Updated successfully at- ".concat(String.valueOf(id)));
        }catch (IndexOutOfBoundsException e){
            logger.error("Index not found at update");
            throw new IndexNotFound("Index Not Found at- ".concat(String.valueOf(id)));
        }
    }

    /**
     * Developer delte mapping
     * @param id
     * @return
     */
    @Profile("dev")
    @DeleteMapping("/vehicleDelete/{id}")
    public ResponseEntity<String> deleteDevVehicle(@PathVariable int id) {
        try {
            VehicleService.deleteVehicles(id);
            return ResponseEntity.ok("Vehicle deleted successfully");
        }catch (IndexOutOfBoundsException e){
            logger.error("Index not found at delete");
            throw new IndexNotFound("Index Not Found at- ".concat(String.valueOf(id)));
        }
    }

    @GetMapping("/vehicle/expensive")
    public VehicleDto getExpensiveVehicle(){
        return VehicleService.mostExpensive();
    }

    @GetMapping("/vehicle/notExpensive")
    public VehicleDto getLeastExpensiveVehicle(){
        return VehicleService.leastExpensive();
    }
}
