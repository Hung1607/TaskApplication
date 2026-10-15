package com.example.taskadministration.error;

public class BadRequestException extends RuntimeException{
    public BadRequestException(){
        super();
    }

    public BadRequestException(String err){
        super(err);
    }
}
