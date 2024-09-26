package com.kdu.smarthome.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="rooms")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    String roomName;

    @ManyToOne
    @JoinColumn(name="house_id")
    HouseEntity houseEntity;
}
