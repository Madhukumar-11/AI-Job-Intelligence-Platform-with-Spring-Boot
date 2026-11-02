package com.madhu.jobintelligenceplatformspringboot.Exception;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(String message){
        super(message);
    }
}
