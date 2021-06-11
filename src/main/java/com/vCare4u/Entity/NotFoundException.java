package com.vCare4u.Entity;

public class NotFoundException extends  RuntimeException{
    public NotFoundException(String message)
    {
        super(message);
    }
}
