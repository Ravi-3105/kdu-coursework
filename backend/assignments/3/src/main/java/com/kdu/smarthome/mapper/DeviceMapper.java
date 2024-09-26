package com.kdu.smarthome.mapper;

import com.kdu.smarthome.dto.request.DeviceDTO;
import com.kdu.smarthome.entities.AddDeviceEntity;
import com.kdu.smarthome.entities.DeviceEntity;
import com.kdu.smarthome.entities.RegisteredDevice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeviceMapper {
    
    private DeviceMapper(){}
    public static DeviceEntity toDeviceEntity(DeviceDTO deviceDTO) {
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setKickstonId(deviceDTO.getKickston_id());
        deviceEntity.setDeviceUsername(deviceDTO.getDevice_username());
        deviceEntity.setDevicePassword(deviceDTO.getDevice_password());

        LocalDateTime manufactureDateTime = LocalDateTime.parse(
                deviceDTO.getManufacture_date_time(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS")
        );
        deviceEntity.setManufactureDateTime(manufactureDateTime);

        deviceEntity.setManufactureFactoryPlace(deviceDTO.getManufacture_factory_place());

        return deviceEntity;
    }

    public static RegisteredDevice toRegisteredDevice() {
        return new RegisteredDevice();
    }
    public static AddDeviceEntity toAddDeviceEntity() {
        return new AddDeviceEntity();
    }
}
