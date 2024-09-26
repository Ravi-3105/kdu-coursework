package com.kdu.smarthome.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * House entity and keys
 */
@Data
@Entity
@Table(name="house")
@NoArgsConstructor
@AllArgsConstructor
public class HouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    String houseName;

    String address;
}
