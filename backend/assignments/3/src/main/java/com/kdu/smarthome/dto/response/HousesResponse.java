package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * House response returned
 */
@Data
@AllArgsConstructor
public class HousesResponse {

    String message;

    String houses;

    HttpStatus httpStatus;
}
