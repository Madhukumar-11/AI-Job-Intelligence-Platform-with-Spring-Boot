package com.madhu.jobintelligenceplatformspringboot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRequestDTO {
    private String title;
    private String company;
    private String location;
    private String skills;
    private String description;
    private String experience;
    private String salary;
    private String applyUrl;
}
