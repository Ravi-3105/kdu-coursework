package com.kdu.smarthome.service;

import com.kdu.smarthome.dao.AddDeviceRepository;
import com.kdu.smarthome.dao.RegisteredDeviceRepository;
import com.kdu.smarthome.dto.request.AddDeviceDTO;
import com.kdu.smarthome.dto.request.DeviceRegisterDTO;
import com.kdu.smarthome.dto.request.UserDTO;
import com.kdu.smarthome.exception.custom.MyCustomException;
import com.kdu.smarthome.mapper.DeviceMapper;
import com.kdu.smarthome.mapper.HouseMapper;
import com.kdu.smarthome.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.util.regex.Pattern;

@Service
@Slf4j
public class DeviceService {

    private RegisteredDeviceRepository registeredDeviceRepository;
    private final UserService userService;
    private final InventoryService inventoryService;
    private final HouseService houseService;
    private final RoomService roomService;

    private final AddDeviceRepository addDeviceRepository;

    @Autowired
    public DeviceService(RegisteredDeviceRepository registeredDeviceRepository, UserService userService,
                         InventoryService inventoryService, HouseService houseService,
                         RoomService roomService, AddDeviceRepository addDeviceRepository) {
        this.registeredDeviceRepository = registeredDeviceRepository;
        this.userService = userService;
        this.inventoryService = inventoryService;
        this.houseService = houseService;
        this.roomService = roomService;
        this.addDeviceRepository = addDeviceRepository;
    }

    /**
     * Regitering the device
     * @param deviceRegisterDTO
     * @return registerend device
     * @throws MyCustomException
     */
    public RegisteredDevice register(DeviceRegisterDTO deviceRegisterDTO) throws MyCustomException {
        try {
            String currentUser = HouseMapper.currentUserName();
            UserDTO user = userService.getUserByUsername(currentUser);
            DeviceEntity deviceEntity = inventoryService.getDeviceByInventory(deviceRegisterDTO.getKickston_id());
            RegisteredDevice registeredDevice = DeviceMapper.toRegisteredDevice();
            registeredDevice.setUserDTO(user);
            registeredDevice.setDeviceEntity(deviceEntity);
            registeredDeviceRepository.save(registeredDevice);
            return registeredDevice;
        }catch (Exception e) {
            throw new MyCustomException("Unexpected error occurred"+e);
        }
    }

    /**
     * Adding device
     * @param addDeviceRequest
     * @return Device details
     */
    public DeviceEntity addDevice(AddDeviceDTO addDeviceRequest) throws Exception {
        AddDeviceEntity addDeviceEntity = DeviceMapper.toAddDeviceEntity();
        String houseId = addDeviceRequest.getHouseId();
        Pattern pattern = Pattern.compile("^\\d+$");

        if(houseId==null ||!pattern.matcher(houseId).matches())
        {
            throw new MyCustomException("House ID not found");
        }
        int houseInt = Integer.parseInt(houseId);

        String roomId = addDeviceRequest.getRoomId();
        if(roomId==null ||!pattern.matcher(roomId).matches())
        {
            throw new MyCustomException("Room ID not found");
        }
        int roomIdInteger = Integer.parseInt(roomId);
        String kickstonId = addDeviceRequest.getKickstonId();

        RegisteredDevice registeredDevice = registeredDeviceRepository.findByKickstonId(kickstonId);
        AddDeviceEntity deviceModel1= addDeviceRepository.findByKickStonId(kickstonId);

        HouseEntity houseEntity = houseService.getHouseById(houseInt);
        if (houseEntity == null) {
            throw new MyCustomException("House ID not found");
        }
        RoomEntity roomEntity = roomService.getRoomByIdAndHouseId(roomIdInteger, houseEntity.getId());
        if (roomEntity == null) {
            throw new MyCustomException("Room ID not found");
        }
        log.info("RoomModel: "+ roomEntity);
        // checking if the device ID is valid and registered
        if (registeredDevice == null) {
            throw new MyCustomException("Device ID not found");
        }
        log.info("registered model: "+registeredDevice);


        String currentUser = HouseMapper.currentUserName();
        HouseUser houseUser = houseService.houseUserRepository.findAllByUserUsernameAndHouseId(currentUser, houseEntity.getId());
        if (houseUser == null) {
            throw new RuntimeException("User not registered under this house");
        }
        DeviceEntity deviceEntity = inventoryService.getDeviceByInventory(kickstonId);
        if(deviceModel1!=null && deviceModel1.getHouseEntity().getId()==houseInt){
           RoomEntity roomEntity1 = roomService.getRoomByIdAndHouseId(deviceModel1.getRoomEntity().getId(),houseInt);
            deviceModel1.setRoomEntity(roomEntity1);
            addDeviceRepository.save(deviceModel1);
            return  deviceModel1.getDeviceEntity();
        }
      else if(deviceModel1==null) {
            addDeviceEntity.setHouseEntity(houseEntity);
            addDeviceEntity.setRoomEntity(roomEntity);
            addDeviceEntity.setDeviceEntity(deviceEntity);
            if (houseUser.getHouseRole().equals(HouseRole.ADMIN))
                addDeviceEntity.setHouseUser(houseUser);

            else {
                throw new MyCustomException("user not admin");
            }

            addDeviceRepository.save(addDeviceEntity);
            return deviceEntity;
        }
      else {
          throw new MyCustomException("Unable to move or add device");
        }


    }
}
