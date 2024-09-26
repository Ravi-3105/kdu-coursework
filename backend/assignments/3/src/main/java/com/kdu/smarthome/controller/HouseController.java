package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.HouseDTO;
import com.kdu.smarthome.dto.request.UserNameDTO;
import com.kdu.smarthome.dto.response.CommonResponse;
import com.kdu.smarthome.dto.response.HousesResponse;
import com.kdu.smarthome.dto.response.HouseResponse;
import com.kdu.smarthome.dto.response.ViewAllResponse;
import com.kdu.smarthome.entities.HouseEntity;
import com.kdu.smarthome.entities.HouseRole;
import com.kdu.smarthome.service.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * House adding and updating controller
 */
@RestController
@Slf4j
@RequestMapping("/api/v1/house")
public class HouseController {

    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PutMapping("/")
    public ResponseEntity<CommonResponse> updateHouse(
            @RequestParam Integer houseId,
            @RequestBody Map<String, String> requestBody) {

        String newAddress = requestBody.get("address");

        if (newAddress == null || newAddress.isEmpty()) {
            CommonResponse commonResponse = new CommonResponse("Address empty", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResponse);
        }

        try {
            HouseEntity updatedHouse = houseService.updateHouseAddress(houseId, newAddress);
            CommonResponse commonResponse = new CommonResponse("House Updated", updatedHouse, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
        } catch (Exception e) {
            CommonResponse commonResponse = new CommonResponse("Error: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(commonResponse);
        }
    }

    @PostMapping
    public HttpEntity<HouseResponse> addHouse(@RequestBody HouseDTO houseDTO) {
        try {
            HouseEntity houseEntity = houseService.addHouse(houseDTO, HouseRole.ADMIN);
            HouseResponse responseVal = new HouseResponse("House added", houseEntity, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(responseVal);
        } catch (Exception e) {
            HouseResponse responseVal = new HouseResponse("Error: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVal);
        }
    }
    @GetMapping("/")
    public ResponseEntity<HousesResponse> getAllHouses() {
        try {
            List<HouseEntity> houseEntityList = houseService.getAllHouses();
            HousesResponse genericResponse = new HousesResponse("House list. ", houseEntityList.toString(), HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
        } catch (Exception e) {
            HousesResponse genericResponse = new HousesResponse("Error: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResponse);
        }
    }

    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<CommonResponse> addUser(
            @PathVariable("houseId") Integer houseId,
            @RequestBody UserNameDTO username) {

        if (username == null || username.getUsername() == null || username.getUsername().isEmpty()) {
            CommonResponse commonResponse = new CommonResponse("Empty username", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResponse);
        }

        try {
            String userName = houseService.addUserToHouse(houseId, username.getUsername());
            CommonResponse commonResponse = new CommonResponse("User aded ", userName, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(commonResponse);

        } catch (Exception e) {
                CommonResponse commonResponse = new CommonResponse("Error: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(commonResponse);
        }
    }
    @GetMapping("/{houseId}")
    public HttpEntity<ViewAllResponse> getAll(@PathVariable String houseId) {
        try {
            Integer houseIDInt = Integer.parseInt(houseId);
            String addDeviceModel = houseService.getAllDetails(houseIDInt);
            ViewAllResponse viewAllResponse = new ViewAllResponse("All Rooms and Devices", addDeviceModel, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(viewAllResponse);

        } catch (Exception e) {
            ViewAllResponse viewAllResponse = new ViewAllResponse("Error: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(viewAllResponse);

        }
    }
}
