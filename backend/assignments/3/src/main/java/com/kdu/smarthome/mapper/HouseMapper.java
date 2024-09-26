package com.kdu.smarthome.mapper;

import com.kdu.smarthome.dto.request.HouseDTO;
import com.kdu.smarthome.dto.request.UserDTO;
import com.kdu.smarthome.entities.HouseEntity;
import com.kdu.smarthome.entities.HouseRole;
import com.kdu.smarthome.entities.HouseUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
public class HouseMapper {

    private HouseMapper(){}
    public static HouseEntity toHouseModel(HouseDTO houseDTO, HouseRole houseRole) {
        HouseEntity houseEntity = new HouseEntity();
        houseEntity.setHouseName(houseDTO.getHouse_name());
        log.info("HOUSEDTO: " + houseDTO.toString());
        houseEntity.setAddress(houseDTO.getAddress());
        return houseEntity;
    }

    public static HouseUser toHouseUser(HouseDTO houseDTO, HouseRole houseRole) {
        log.info("HouseDto {}", houseDTO);
        HouseUser houseUser = new HouseUser();
        houseUser.setHouseRole(houseRole);
        String username = currentUserName();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        houseUser.setUser(userDTO);
        return houseUser;
    }

    public static String currentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
