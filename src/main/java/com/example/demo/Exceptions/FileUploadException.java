package com.example.demo.Exceptions;

public class FileUploadException extends RuntimeException{

    public FileUploadException(String sms){
        super(sms);
    }
}
