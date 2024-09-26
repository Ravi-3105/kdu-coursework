package com.kdu.smarthome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Device adding details
 */
@Data
@AllArgsConstructor
public class AddDeviceDTO {

    private String houseId;


    private String roomId;

    private String kickstonId;
}
