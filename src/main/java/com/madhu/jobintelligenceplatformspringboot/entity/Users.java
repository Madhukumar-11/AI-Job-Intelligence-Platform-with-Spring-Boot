package com.madhu.jobintelligenceplatformspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username",nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "password",nullable = false,length = 255)
    private String password;

    @Column(name = "role",nullable = false,length = 20)
    private String role;
}
