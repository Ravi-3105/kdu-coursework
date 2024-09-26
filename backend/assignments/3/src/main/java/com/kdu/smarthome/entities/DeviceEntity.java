package com.kdu.smarthome.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "device")
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "kickston_id", unique = true)
    private String kickstonId;

    @Column(name = "device_username")
    private String deviceUsername;

    @Column(name = "device_password")
    private String devicePassword;

    @Column(name = "manufacture_date_time")
    private LocalDateTime manufactureDateTime;

    @Column(name = "manufacture_factory_place")
    private String manufactureFactoryPlace;
}
