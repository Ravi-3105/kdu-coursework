package com.kdu.smarthome.mapper;

import com.kdu.smarthome.dto.request.RoomDTO;
import com.kdu.smarthome.entities.HouseEntity;
import com.kdu.smarthome.entities.RoomEntity;

public class RoomMapper {

    private RoomMapper(){}

    public static RoomEntity dtomodelmapper(RoomDTO roomDTO, HouseEntity houseEntity) {
        RoomEntity roomEntity = new RoomEntity();
        HouseEntity houseEntityObj = houseEntity;
        houseEntityObj.setHouseName(houseEntityObj.getHouseName());
        roomEntity.setRoomName(roomDTO.getRoom_name());
        roomEntity.setHouseEntity(houseEntityObj);
        return roomEntity;
    }
}
