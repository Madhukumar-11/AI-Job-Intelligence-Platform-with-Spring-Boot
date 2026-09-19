package com.madhu.jobintelligenceplatformspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = "title") - use this when you don't want mismatch
    //                          that means when your variable is different from column name in mySQL table
    @Column(name = "title", nullable = false, length = 200)
    private String title;
    @Column(name = "company", nullable = false, length = 200)
    private String company;
    @Column(name = "location", nullable = false, length = 200)
    private String location;
    @Column(name = "skills", nullable = false, length = 200)
    private String skills;
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;
    @Column(name = "experience", length = 100)
    private String experience;
    @Column(name = "salary", length = 100)
    private String salary;
    @Column(name = "apply_url", length = 500)
    private String applyUrl;
}
