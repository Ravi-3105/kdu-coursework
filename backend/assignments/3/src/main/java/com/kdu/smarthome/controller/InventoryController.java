package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.DeviceDTO;
import com.kdu.smarthome.dto.response.CommonResponse;
import com.kdu.smarthome.dto.response.InventoryResponse;
import com.kdu.smarthome.entities.DeviceEntity;
import com.kdu.smarthome.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Adding and displaying inventory
 */

@RestController
@Slf4j
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse> add(@RequestBody DeviceDTO deviceDTO) {
        try {
            DeviceEntity deviceEntity = inventoryService.add(deviceDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new CommonResponse("Inventory added successfully", deviceEntity, HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse("Unexpected error: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping
    public ResponseEntity<InventoryResponse> display() {
        try {
            String inventoryList = inventoryService.getAllDevices();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new InventoryResponse("Inventory retrieved successfully", inventoryList, HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new InventoryResponse("Failed to retrieve inventory: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
