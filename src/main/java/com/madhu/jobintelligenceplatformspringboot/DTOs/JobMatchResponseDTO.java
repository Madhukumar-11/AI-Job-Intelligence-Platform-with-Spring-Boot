package com.madhu.jobintelligenceplatformspringboot.DTOs;

import com.madhu.jobintelligenceplatformspringboot.entity.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class JobMatchResponseDTO {
    private JobResponseDTO job;
    private double matchScore;
    private List<String> matchingSkills;
    private List<String> missingSkills;
}
