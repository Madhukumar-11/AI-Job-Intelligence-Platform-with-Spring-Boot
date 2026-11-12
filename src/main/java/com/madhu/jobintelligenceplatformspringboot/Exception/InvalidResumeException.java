package com.madhu.jobintelligenceplatformspringboot.Exception;

public class InvalidResumeException extends RuntimeException {
    public InvalidResumeException(String message){
        super(message);
    }
}
