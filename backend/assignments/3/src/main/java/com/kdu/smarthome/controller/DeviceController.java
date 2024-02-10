package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddDeviceDTO;
import com.kdu.smarthome.dto.request.DeviceRegisterDTO;
import com.kdu.smarthome.dto.response.CommonResponse;
import com.kdu.smarthome.entities.DeviceEntity;
import com.kdu.smarthome.entities.RegisteredDevice;
import com.kdu.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Device add and regiter controller
 */

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/register")
    public ResponseEntity<CommonResponse> registerDevice(@RequestBody DeviceRegisterDTO deviceRegisterDTO) {
        try {
            RegisteredDevice registeredDevice = deviceService.register(deviceRegisterDTO);
            return ResponseEntity.ok(new CommonResponse("Device Registered", registeredDevice, HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse("Error: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
    @PostMapping("/add")
    public ResponseEntity<CommonResponse> addDevice(@RequestBody AddDeviceDTO addDeviceRequest) {
        try {
            DeviceEntity deviceEntity = deviceService.addDevice(addDeviceRequest);
            return ResponseEntity.ok(new CommonResponse("Device added", deviceEntity, HttpStatus.OK));
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse("Error: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
