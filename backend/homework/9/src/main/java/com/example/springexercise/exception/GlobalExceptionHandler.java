package com.example.springexercise.exception;

import com.example.springexercise.Dto.ErrorDTO;
import com.example.springexercise.exception.custom.IndexNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Custom exception class used
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {IndexNotFound.class})
    public ResponseEntity<ErrorDTO> handleCustomException(IndexNotFound ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage().concat(" [IndexNotFound]"), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return  new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * handles global exception
     * @param ex
     * @return
     */
    @ExceptionHandler(value =  {Exception.class})
    public ResponseEntity<ErrorDTO> allKindOfExceptions(Exception ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage().concat(" [Parent Exception]"), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return  new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
