package com.kdu.smarthome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * All device details
 */
@Data
@AllArgsConstructor
public class DeviceDTO {
    private String kickston_id;

    private String device_username;

    private String device_password;

    private String manufacture_date_time;

    private String manufacture_factory_place;
}
