package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.entities.RoomEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Response about room returned
 */
@Data
@AllArgsConstructor
public class RoomResponse {

    String message;

    RoomEntity room ;

    HttpStatus httpStatus;
}
