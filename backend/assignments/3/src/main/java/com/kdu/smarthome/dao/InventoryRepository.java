package com.kdu.smarthome.dao;

import com.kdu.smarthome.entities.DeviceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<DeviceEntity,Integer> {

    @Query("SELECT d FROM DeviceEntity d WHERE d.kickstonId = :kickstonId")
    DeviceEntity findByKickstonId(String kickstonId);

}
