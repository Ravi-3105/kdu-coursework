package com.kdu.smarthome.dto.request;

import lombok.Data;
import lombok.AllArgsConstructor;

/**
 * House details
 */
@Data
@AllArgsConstructor
public class HouseDTO {

    String address;

    String house_name;
}
