package com.madhu.jobintelligenceplatformspringboot.Exception;

public class UserExists extends RuntimeException{
    public UserExists(String message){
        super(message);
    }
}
