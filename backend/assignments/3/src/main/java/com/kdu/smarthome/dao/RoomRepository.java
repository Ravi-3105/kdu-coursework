package com.kdu.smarthome.dao;

import com.kdu.smarthome.entities.RoomEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<RoomEntity, Integer> {

    @Query("SELECT room FROM RoomEntity room WHERE room.id = :id AND room.houseEntity.id = :houseID")
    RoomEntity findIdAndHouseId(@Param("id") Integer roomID, @Param("houseID") Integer houseID);
}
