package com.kdu.smarthome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Device registration details
 */
@Data
@AllArgsConstructor
public class DeviceRegisterDTO {

    String kickston_id;

    String device_username;

    String device_password;
}
