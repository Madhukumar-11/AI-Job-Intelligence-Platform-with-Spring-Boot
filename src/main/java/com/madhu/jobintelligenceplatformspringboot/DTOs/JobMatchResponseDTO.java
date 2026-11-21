package com.madhu.jobintelligenceplatformspringboot.DTOs;

import com.madhu.jobintelligenceplatformspringboot.entity.Job;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class JobMatchResponseDTO {
    private JobResponseDTO job;
    private double matchScore;
    private List<String> matchingSkills;
    private List<String> missingSkills;
}
