package com.madhu.jobintelligenceplatformspringboot.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private String username;
    private String password;
}
