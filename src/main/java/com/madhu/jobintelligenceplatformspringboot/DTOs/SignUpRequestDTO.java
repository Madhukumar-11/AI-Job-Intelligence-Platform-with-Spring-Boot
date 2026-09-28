package com.madhu.jobintelligenceplatformspringboot.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {
    private String username;
    private String password;
    private String role;
}
