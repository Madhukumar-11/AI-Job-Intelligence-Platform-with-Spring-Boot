package com.madhu.jobintelligenceplatformspringboot.Controller;

import com.madhu.jobintelligenceplatformspringboot.DTOs.LoginRequestDTO;
import com.madhu.jobintelligenceplatformspringboot.DTOs.LoginResponseDTO;
import com.madhu.jobintelligenceplatformspringboot.DTOs.SignUpRequestDTO;
import com.madhu.jobintelligenceplatformspringboot.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public String createUser(@RequestBody SignUpRequestDTO signUpRequestDTO){
        return authService.signUp(signUpRequestDTO);
    }

    @PostMapping("/login")
    public LoginResponseDTO signIn(@RequestBody LoginRequestDTO loginRequestDTO){
        return authService.login(loginRequestDTO);
    }

}
