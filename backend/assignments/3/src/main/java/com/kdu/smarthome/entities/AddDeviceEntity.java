package com.kdu.smarthome.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Device entity and tables
  */
@Entity
@Data
@RequiredArgsConstructor
@Table(name="added_devices")
public class AddDeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "kickston_id")
    DeviceEntity deviceEntity;

    @ManyToOne
    @JoinColumn(name="room_id")
    RoomEntity roomEntity;

    @ManyToOne
    @JoinColumn(name="house_id")
    HouseEntity houseEntity;

    @ManyToOne
    @JoinColumn(name = "house_user_id")
    private HouseUser houseUser;
}
