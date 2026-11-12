package com.madhu.jobintelligenceplatformspringboot;

import com.madhu.jobintelligenceplatformspringboot.Service.JobMatchService;
import com.madhu.jobintelligenceplatformspringboot.entity.Job;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JobIntelligencePlatformSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobIntelligencePlatformSpringBootApplication.class, args);

//        List<String> skills1 = new ArrayList<>();
//        skills1.add("Java");
//        skills1.add("Python");
//        skills1.add("C#");
//        skills1.add("MySQL");
//        String skills = skills1.toString();
//
//        Job job = new Job(0,null,null,null,skills,null,null,null,null);
//
//        List<String> resumeSkills = new ArrayList<>();
//        resumeSkills.add("Java");
//        resumeSkills.add("Python");
//        resumeSkills.add("sql");
//
//        //String resumeSkills = resumeSkills1.toString();
//
//        JobMatchService jobMatchService = new JobMatchService();
//        System.out.println(jobMatchService.matchJob(resumeSkills,job));
    }

}
