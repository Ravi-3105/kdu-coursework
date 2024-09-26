package com.kdu.smarthome.service;

import com.kdu.smarthome.dao.InventoryRepository;
import com.kdu.smarthome.dto.request.DeviceDTO;
import com.kdu.smarthome.exception.custom.MyCustomException;
import com.kdu.smarthome.mapper.DeviceMapper;
import com.kdu.smarthome.entities.DeviceEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryService {
    InventoryRepository inventoryRepository;

    InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Add device to inventry
     * @param deviceDTO
     * @return Device Entity
     */
    public DeviceEntity add(DeviceDTO deviceDTO) throws MyCustomException {
        DeviceEntity deviceEntity = DeviceMapper.toDeviceEntity(deviceDTO);
        try {
            return inventoryRepository.save(deviceEntity);
        } catch (Exception e) {
            throw new MyCustomException("Error");
        }
    }

    /**
     * Get all devices
     * @return all devices
     */
    public String getAllDevices() throws MyCustomException {
        try {
            return inventoryRepository.findAll().toString();
        } catch (Exception e) {
           throw  new MyCustomException("Error");
        }
    }

    /**
     * finding device by id
     * @param kickstonId
     * @return
     */
    public DeviceEntity getDeviceByInventory(String kickstonId) throws MyCustomException {
        DeviceEntity deviceEntity = inventoryRepository.findByKickstonId(kickstonId);
        if (deviceEntity == null) {
            throw new MyCustomException("Kickston_id not found");
        }
        return deviceEntity;
    }
}
