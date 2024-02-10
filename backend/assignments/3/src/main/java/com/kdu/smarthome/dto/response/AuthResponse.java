package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Auth response returned
 */
@Data
@AllArgsConstructor
public class AuthResponse {

    String message;

    String token;

    HttpStatus httpStatus;
}
