package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.entities.HouseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Represents a response for house operations.
 */
@Data
@AllArgsConstructor
public class HouseResponse {
    String message;

    HouseEntity house;

    HttpStatus httpStatus;
}
