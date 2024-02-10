package com.kdu.smarthome.service;

import com.kdu.smarthome.dao.AddDeviceRepository;
import com.kdu.smarthome.dao.HouseRepository;
import com.kdu.smarthome.dao.HouseUserRepository;
import com.kdu.smarthome.dto.request.HouseDTO;
import com.kdu.smarthome.dto.request.UserDTO;
import com.kdu.smarthome.exception.custom.MyCustomException;
import com.kdu.smarthome.mapper.HouseMapper;
import com.kdu.smarthome.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HouseService {
    HouseRepository houseRepository;
    HouseUserRepository houseUserRepository;
    AddDeviceRepository addDeviceRepository;
    UserService userService;

    @Autowired
    HouseService(HouseRepository houseRepository, HouseUserRepository houseUserRepository, UserService userService, AddDeviceRepository addDeviceRepository) {
        this.houseRepository = houseRepository;
        this.houseUserRepository = houseUserRepository;
        this.userService = userService;
        this.addDeviceRepository = addDeviceRepository;
    }

    /**
     * House added by admin
     * @param houseDTO
     * @param admin
     * @return House entity
     */
    public HouseEntity addHouse(HouseDTO houseDTO, HouseRole admin) throws Exception {
        try {
            HouseEntity houseEntity = HouseMapper.toHouseModel(houseDTO, admin);
            houseEntity = houseRepository.save(houseEntity);
            HouseUser houseUser = HouseMapper.toHouseUser(houseDTO, admin);
            UserDTO userDTO = userService.getUserByUsername(houseUser.getUser().getUsername());
            if (userDTO == null) {
                throw new RuntimeException("User not found" + houseUser.getUser().getUsername());
            }
            houseUser.setUser(userDTO);
            houseUser.setHouse(houseEntity);
            houseUserRepository.save(houseUser);

            return houseEntity;
        } catch (Exception e) {
            throw new MyCustomException("Error: " + e);
        }
    }

    /**
     * Add user to house
     * @param houseId
     * @param newUser
     * @return String details
     */
    public String addUserToHouse(Integer houseId, String newUser) throws Exception {
        try {
            String currentUser = HouseMapper.currentUserName();
            Optional<HouseUser> houseModel = houseUserRepository.findById(houseId);
            if (houseModel.isPresent()) {
                HouseUser house = houseModel.get();
                if (house.getUser().getUsername().equals(currentUser)) {
                    UserDTO userDTO = userService.getUserByUsername(newUser);
                    HouseUser houseUser = new HouseUser();
                    houseUser.setUser(userDTO);
                    houseUser.setHouseRole(HouseRole.BASIC);
                    houseUser.setHouse(house.getHouse());
                    houseUserRepository.save(houseUser);

                    return userDTO.getName();
                } else {
                    throw new RuntimeException("User not admin");
                }
            }
            throw new MyCustomException("Unable to add user");
        } catch (Exception e) {
            throw new MyCustomException("Error: " + e);
        }
    }

    /**
     * Search for house
     * @param houseId
     * @return found HouseEntity
     */
    public HouseEntity getHouseById(Integer houseId) throws Exception {
        try {
            Optional<HouseEntity> optionalHouseModel = houseRepository.findById(houseId);
            if (optionalHouseModel.isPresent()) {
                return optionalHouseModel.get();
            } else {
                throw new MyCustomException("House with ID " + houseId + " not found");
            }
        } catch (Exception e) {
            throw new MyCustomException("Error: " + e);
        }
    }

    /**
     * updates house address
     * @param houseId
     * @param newAddress
     * @return House updated entity
     */
    public HouseEntity updateHouseAddress(Integer houseId, String newAddress) throws Exception {
        try {
            Optional<HouseEntity> optionalHouseModel = houseRepository.findById(houseId);
            if (optionalHouseModel.isPresent()) {
                HouseEntity houseEntity = optionalHouseModel.get();
                houseEntity.setAddress(newAddress);
                return houseRepository.save(houseEntity);
            } else {
                throw new MyCustomException("House with ID " + houseId + " not found");
            }
        } catch (Exception e) {
            throw new MyCustomException("Error: " + e);
        }
    }

    /**
     * Geti all houses
     * @return List of houses
     * @throws MyCustomException
     */
    public List<HouseEntity> getAllHouses() throws MyCustomException {
        try {
            String currentUser = HouseMapper.currentUserName();
            List<HouseUser> houseUsers = houseUserRepository.findAllByUserUsername(currentUser);

            List<HouseEntity> userHouses = new ArrayList<>();

            for (HouseUser houseUser : houseUsers) {

                Integer houseId = houseUser.getHouse().getId();
                HouseEntity houseEntity = houseRepository.findById(houseId).orElse(null);
                if (houseEntity != null || houseEntity.getAddress() == null) {
                    userHouses.add(houseEntity);
                }
            }

            return userHouses;

        } catch (Exception e) {
            throw new MyCustomException("Error: " + e);
        }
    }

    /**
     * Getting all details
     * @param houseID
     * @return details string
     */
    public String getAllDetails(Integer houseID) {
        List<Object[]> roomsAndDevices = addDeviceRepository.findHouseDeviceRoomByHouseId(houseID);
        StringBuilder result = new StringBuilder();
        for (Object[] row : roomsAndDevices) {
            HouseEntity houseEntity = (HouseEntity) row[0];
            DeviceEntity deviceEntity = (DeviceEntity) row[1];
            RoomEntity roomEntity = (RoomEntity) row[2];

            result.append("House: ").append(houseEntity.toString()).append(", ");
            result.append("Device: ").append(deviceEntity.toString()).append(", ");
            result.append("Room: ").append(roomEntity.toString()).append("\n");
        }
        return result.toString();
    }

}
