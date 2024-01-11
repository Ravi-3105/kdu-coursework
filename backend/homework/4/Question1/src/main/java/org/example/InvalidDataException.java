package org.example;

public class InvalidDataException extends RuntimeException{
    InvalidDataException(){
        super();
        Logging.print("Unchecked Exception caused By MissingGradeException");
    }
}
