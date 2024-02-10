package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.RoomDTO;
import com.kdu.smarthome.dto.response.RoomResponse;
import com.kdu.smarthome.entities.RoomEntity;
import com.kdu.smarthome.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Adding room controller
 */
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<RoomResponse> addRoomToHouse(
            @RequestParam("houseId") Integer houseId,
            @RequestBody RoomDTO roomDTO) {

        if (houseId == null) {
            RoomResponse response = new RoomResponse("HouseId cannot be empty", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (roomDTO == null || roomDTO.getRoom_name() == null || roomDTO.getRoom_name().isEmpty()) {
            RoomResponse response = new RoomResponse("Room name cannot be empty", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            RoomEntity roomEntity = roomService.addRoom(roomDTO, houseId);
            return ResponseEntity.status(HttpStatus.OK).body(new RoomResponse("Room added successfully", roomEntity, HttpStatus.OK));
        } catch (Exception e) {

                RoomResponse response = new RoomResponse("Failed to add room to the house: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
