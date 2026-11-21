package com.madhu.jobintelligenceplatformspringboot.Service;

import com.madhu.jobintelligenceplatformspringboot.DTOs.JobMatchResponseDTO;
import com.madhu.jobintelligenceplatformspringboot.Repository.JobRepository;
import com.madhu.jobintelligenceplatformspringboot.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobMatchService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobService jobService;

    private static final Map<String, String> aliases = Map.of(
            "mysql", "sql",
            "postgresql", "sql",
            "postgres", "sql",
            "js", "javascript",
            "react.js","react",
            "k8s","kubernetes",
            "reactjs","react"
    );

    private String normalize(String skill){
        skill = skill.trim().toLowerCase();
        return aliases.getOrDefault(skill,skill);
    }

    public JobMatchResponseDTO matchJob(List<String> resumeSkills, Job job){
        List<String> skillsMatched;
        List<String> skillsNotMatched;
        double matchScore;
        if(job.getSkills() == null || job.getSkills().isBlank()){
            skillsMatched = new ArrayList<>();
            skillsNotMatched = new ArrayList<>();
            matchScore = 0;
        }else {
            List<String> jobSkills = Arrays.stream(job.getSkills().split(","))
                    .map(String::trim)
                    .filter(skill -> !skill.isBlank())
                    .toList();
            List<String> normalizedResumeSkills;

            if (resumeSkills != null) {
                normalizedResumeSkills = resumeSkills.stream()
                        .filter(Objects::nonNull)
                        .map(this::normalize)
                        .distinct()
                        .toList();
            }else{
                normalizedResumeSkills = Collections.emptyList();
            }

            skillsMatched = new ArrayList<>();
            skillsNotMatched = new ArrayList<>();

            for (String skill : jobSkills) {   // just trimmed, exactly as the job wrote it
                String key = normalize(skill);      // the standard name, used only for comparing
                if (normalizedResumeSkills.contains(key)) {
                    skillsMatched.add(skill);
                } else {
                    skillsNotMatched.add(skill);
                }
            }

            double totalSkills = jobSkills.size();
            double totalMatches = skillsMatched.size();

            if (totalSkills != 0) {
                matchScore = Math.round((totalMatches / totalSkills) * 100);
            } else {
                matchScore = 0;
            }
        }
//        System.out.println("Match score: " + matchScore);
//        System.out.println("Matched Skills: " + skillsMatched);
//        System.out.println("Not Matches Skills: " + skillsNotMatched);
//        return job;
        return new JobMatchResponseDTO(jobService.convertToResponseDTO(job), matchScore,
                skillsMatched, skillsNotMatched);
    }

    public List<JobMatchResponseDTO> matchAllJobs(List<String> resumeSkills){
        return jobRepository.findAll().stream()
                .map(job -> matchJob(resumeSkills, job))
                .filter(result -> result.getMatchScore() > 0)
                .sorted(Comparator.comparingDouble(JobMatchResponseDTO::getMatchScore).reversed())
                .toList();
    }
}
