package com.kdu.smarthome.entities;

import com.kdu.smarthome.dto.request.UserDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="registered_device")
public class RegisteredDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserDTO userDTO;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private DeviceEntity deviceEntity;
}
