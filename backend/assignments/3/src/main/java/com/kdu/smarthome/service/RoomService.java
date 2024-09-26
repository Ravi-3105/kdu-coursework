package com.kdu.smarthome.service;

import com.kdu.smarthome.dao.HouseUserRepository;
import com.kdu.smarthome.dao.RoomRepository;
import com.kdu.smarthome.dto.request.RoomDTO;
import com.kdu.smarthome.exception.custom.MyCustomException;
import com.kdu.smarthome.mapper.HouseMapper;
import com.kdu.smarthome.mapper.RoomMapper;
import com.kdu.smarthome.entities.HouseEntity;
import com.kdu.smarthome.entities.HouseUser;
import com.kdu.smarthome.entities.RoomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    HouseService houseService;
    RoomRepository roomRepository;
    HouseUserRepository houseDAO;

    @Autowired
    RoomService(HouseService houseService, RoomRepository roomRepository, HouseUserRepository houseUser) {
        this.houseService = houseService;
        this.roomRepository = roomRepository;
        this.houseDAO = houseUser;
    }

    /**
     * Add room to house
     * @return Room Entity
     */
    public RoomEntity addRoom(RoomDTO room, Integer houseID) throws Exception {
        HouseEntity houseEntity = houseService.getHouseById(houseID);
        Optional<HouseUser> houseUserOpt = houseDAO.findById(houseID);

        if (houseUserOpt.isPresent()) {
            HouseUser houseUser = houseUserOpt.get();
            if (HouseMapper.currentUserName().equals(houseUser.getUser().getUsername())) {
                RoomEntity roomEntity = RoomMapper.dtomodelmapper(room, houseEntity);
                roomRepository.save(roomEntity);
                return roomEntity;
            } else {
                throw new MyCustomException("Admin privilege required");
            }
        } else {
            throw new MyCustomException("House with ID " + houseID + " not found");
        }
    }

    /**
     * Get room in the house by id
     * @param roomId
     * @param houseId
     * @return Room Entity
     */
    public RoomEntity getRoomByIdAndHouseId(int roomId, int houseId) {
        return roomRepository.findIdAndHouseId(roomId, houseId);
    }
}
