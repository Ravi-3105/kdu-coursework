package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Common/unknown response returned
 */
@Data
@AllArgsConstructor
public class CommonResponse {

    String message;

    Object object;

    HttpStatus httpStatus;
}
