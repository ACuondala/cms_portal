package com.example.demo.Exceptions;

public class EntitiesNotFoundException extends RuntimeException{

    public EntitiesNotFoundException(String sms){
        super(sms);
    }
}
