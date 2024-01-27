package com.caching.exception.custom;

public class MyCustomException extends IndexOutOfBoundsException{
    public MyCustomException(String s){
        super(s);
    }
}
