package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Universal response returned
 */
@Data
@AllArgsConstructor
public class ViewAllResponse {

    String message;

    String roomsAndDevices;

    HttpStatus httpStatus;
}
