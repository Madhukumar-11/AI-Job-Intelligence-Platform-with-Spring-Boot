package com.madhu.jobintelligenceplatformspringboot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseDTO {
    private int id;
    private String title;
    private String company;
    private String location;
    private String skills;
    private String description;
    private String experience;
    private String salary;
    private String applyUrl;
}
