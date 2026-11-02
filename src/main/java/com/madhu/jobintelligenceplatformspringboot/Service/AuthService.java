package com.madhu.jobintelligenceplatformspringboot.Service;

import com.madhu.jobintelligenceplatformspringboot.DTOs.LoginRequestDTO;
import com.madhu.jobintelligenceplatformspringboot.DTOs.LoginResponseDTO;
import com.madhu.jobintelligenceplatformspringboot.DTOs.SignUpRequestDTO;
import com.madhu.jobintelligenceplatformspringboot.Exception.InvalidCredentialsException;
import com.madhu.jobintelligenceplatformspringboot.Exception.UserExists;
import com.madhu.jobintelligenceplatformspringboot.Repository.UserRepository;
import com.madhu.jobintelligenceplatformspringboot.Security.JwtUtil;
import com.madhu.jobintelligenceplatformspringboot.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    /* @Autowired
    PasswordEncoderConfig passwordEncoderConfig; -- No need of this class to create object every time
    cause we only want passwordEncoder() method and already we defined @Bean for it
    so we just need to create passwordEncoder so that Spring manages that method as Bean for us when we required */
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtil jwtUtil;

    public String signUp(SignUpRequestDTO signUpRequestDTO){
        Users users = new Users();
        if(userRepository.findByUsername(signUpRequestDTO.getUsername()).isPresent()){
            throw new UserExists("Username is already taken");
        }
        users.setUsername(signUpRequestDTO.getUsername());
        users.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
        users.setRole("USER");

        userRepository.save(users);
        return "Sign-Up Successfull";
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        Users loginDetails = userRepository.findByUsername(loginRequestDTO.getUsername())
                            .orElseThrow(()-> new InvalidCredentialsException("Invalid Credentials"));
        if(!passwordEncoder.matches(loginRequestDTO.getPassword(), loginDetails.getPassword())){
                            throw new InvalidCredentialsException(("Invalid Credentials"));
        }
        String token = jwtUtil.generateToken(loginDetails.getUsername(), loginDetails.getRole());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO("Login Successfull",token,
                                                                 loginDetails.getRole());
        return loginResponseDTO;
    }
}
