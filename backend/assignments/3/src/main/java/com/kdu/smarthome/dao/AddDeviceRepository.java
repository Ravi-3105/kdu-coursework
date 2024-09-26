package com.kdu.smarthome.dao;

import com.kdu.smarthome.entities.AddDeviceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddDeviceRepository extends CrudRepository<AddDeviceEntity,Integer> {

    @Query("SELECT ad.houseEntity, ad.deviceEntity, ad.roomEntity FROM AddDeviceEntity ad WHERE ad.houseEntity.id = :houseId")
    List<Object[]> findHouseDeviceRoomByHouseId(@Param("houseId") Integer houseId);

    @Query("SELECT ad FROM AddDeviceEntity ad WHERE ad.deviceEntity.kickstonId = :kickston_id")
    AddDeviceEntity findByKickStonId(@Param("kickston_id") String kickstonId);

}
