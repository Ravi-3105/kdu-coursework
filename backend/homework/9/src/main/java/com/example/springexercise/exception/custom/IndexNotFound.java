package com.example.springexercise.exception.custom;

public class IndexNotFound extends IndexOutOfBoundsException{
    public IndexNotFound(String s){
        super(s);
    }
}
