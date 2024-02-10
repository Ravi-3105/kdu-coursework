package com.kdu.smarthome.dao;

import com.kdu.smarthome.entities.RegisteredDevice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RegisteredDeviceRepository extends CrudRepository<RegisteredDevice,Integer> {

    
    @Query("SELECT d FROM RegisteredDevice d WHERE d.deviceEntity.kickstonId = :kickstonId")
    RegisteredDevice findByKickstonId(String kickstonId);
}
