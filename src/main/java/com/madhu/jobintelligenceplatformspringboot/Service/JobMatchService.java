package com.madhu.jobintelligenceplatformspringboot.Service;

import com.madhu.jobintelligenceplatformspringboot.DTOs.JobMatchResponseDTO;
import com.madhu.jobintelligenceplatformspringboot.Repository.JobRepository;
import com.madhu.jobintelligenceplatformspringboot.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class JobMatchService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobService jobService;

    public JobMatchResponseDTO matchJob(List<String> resumeSkills, Job job){
        List<String> skillsMatched;
        List<String> skillsNotMatched;
        double matchScore;
        if(job.getSkills() == null || job.getSkills().isBlank()){
            skillsMatched = new ArrayList<>();
            skillsNotMatched = new ArrayList<>();
            matchScore = 0;
        }else {
            List<String> lowerCaseResumeSkills = resumeSkills.stream().map(s -> s.trim().toLowerCase()).toList();
            List<String> jobSkills = List.of(job.getSkills().split(","));

            skillsMatched = new ArrayList<>();
            skillsNotMatched = new ArrayList<>();

            for (String skill : jobSkills) {
                String cleaned = skill.trim();                        // the trimmed copy, saved this time
                if (lowerCaseResumeSkills.contains(cleaned.toLowerCase())) {   // compare in lowercase and also compares substrings
                    skillsMatched.add(cleaned);
                } else {
                    skillsNotMatched.add(cleaned);
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

    public List<JobMatchResponseDTO> matchAlljobs(List<String> resumeSkills){
        return jobRepository.findAll().stream()
                .map(job -> matchJob(resumeSkills, job))
                .filter(result -> result.getMatchScore() > 0)
                .sorted(Comparator.comparingDouble(JobMatchResponseDTO::getMatchScore).reversed())
                .toList();
    }
}
