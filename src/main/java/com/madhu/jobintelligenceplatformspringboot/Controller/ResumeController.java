package com.madhu.jobintelligenceplatformspringboot.Controller;

import com.madhu.jobintelligenceplatformspringboot.DTOs.JobMatchResponseDTO;
import com.madhu.jobintelligenceplatformspringboot.Service.JobMatchService;
import com.madhu.jobintelligenceplatformspringboot.Service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    ResumeService resumeService;

    @Autowired
    JobMatchService jobMatchService;

    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        return resumeService.extractText(file);
    }

    @GetMapping("/match")
    public List<JobMatchResponseDTO> getMatchAlljobs(){
        List<String> resumeSkills = List.of("Java","Spring Boot","MYSQL");
        return jobMatchService.matchAlljobs(resumeSkills);
    }
}
