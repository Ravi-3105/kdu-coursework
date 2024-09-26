package com.kdu.smarthome.entities;

import com.kdu.smarthome.dto.request.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "house_user",indexes = @Index(name = "idx_house_id", columnList = "house_id"))
@Data
public class HouseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "house_id")
    private HouseEntity house;

    @ManyToOne
    @JoinColumn(name = "username")
    private UserDTO user;


    @Column(name="house_role")
    @Enumerated(EnumType.STRING)
    HouseRole houseRole;

}
