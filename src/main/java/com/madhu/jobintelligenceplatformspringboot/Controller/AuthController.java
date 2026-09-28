package com.madhu.jobintelligenceplatformspringboot.Controller;

import com.madhu.jobintelligenceplatformspringboot.DTOs.LoginRequestDTO;
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
        AuthService.signUp(signUpRequestDTO);
        return "User Created Successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO loginRequestDTO){
        AuthService.login(loginRequestDTO);
        return "Login Successfull";
    }

}
