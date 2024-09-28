package com.caching.exception;

import com.caching.dto.ErrorDTO;
import com.caching.exception.custom.MyCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MyCustomException.class})
    public ResponseEntity<ErrorDTO> handleCustomException(MyCustomException ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage().concat(" [MyCustumException]"), HttpStatus.BAD_REQUEST.value());
        return  new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value =  {Exception.class})
    public ResponseEntity<ErrorDTO> allKindOfExceptions(Exception ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage().concat(" [Parent Exception]"), HttpStatus.BAD_REQUEST.value());
        return  new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
