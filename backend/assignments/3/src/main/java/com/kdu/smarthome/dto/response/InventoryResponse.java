package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Inventry response returned
 */
@Data
@AllArgsConstructor
public class InventoryResponse {

        String message;

        String inventory;

        HttpStatus httpStatus;
}
