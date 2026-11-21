package com.madhu.jobintelligenceplatformspringboot.Service;

import com.madhu.jobintelligenceplatformspringboot.DTOs.JobMatchResponseDTO;
import com.madhu.jobintelligenceplatformspringboot.entity.Job;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JobMatchServiceTest {

    @Mock
    private JobService jobService;

    @InjectMocks
    private JobMatchService jobMatchService;

    @Test
    void analyzeResumeTest1() {
        Job job = new Job();
        List<String> resumeSkills = List.of("Java", "MySQL", "Spring Boot");
        job.setSkills("Java,MySQL,Spring Boot");
        JobMatchResponseDTO jobMatchResponseDTO = jobMatchService.matchJob(resumeSkills, job);

        assertEquals(100,jobMatchResponseDTO.getMatchScore());
        assertEquals(List.of("Java", "MySQL", "Spring Boot"),jobMatchResponseDTO.getMatchingSkills());
        assertEquals(List.of(),jobMatchResponseDTO.getMissingSkills());
    }

    @Test
    void analyzeResumeTest2() {
        Job job = new Job();
        List<String> resumeSkills = List.of("Java", "Spring Boot", "MySQL");
        job.setSkills("Python, Django");
        JobMatchResponseDTO jobMatchResponseDTO = jobMatchService.matchJob(resumeSkills, job);
        assertEquals(0,jobMatchResponseDTO.getMatchScore());
        assertEquals(List.of(),jobMatchResponseDTO.getMatchingSkills());
        assertEquals(List.of("Python", "Django"),jobMatchResponseDTO.getMissingSkills());
    }

    @Test
    void analyzeResumeTest3() {
        Job job = new Job();
        List<String> resumeSkills = List.of("JavaScript","Nodejs", "react");
        job.setSkills("Python, React, FastApi");
        JobMatchResponseDTO jobMatchResponseDTO = jobMatchService.matchJob(resumeSkills, job);
        assertEquals(33,jobMatchResponseDTO.getMatchScore());
        assertEquals(List.of("React"),jobMatchResponseDTO.getMatchingSkills());
        assertEquals(List.of("Python", "FastApi"),jobMatchResponseDTO.getMissingSkills());
    }

    @Test
    void analyzeResumeTest4() {
        Job job = new Job();
        List<String> resumeSkills = List.of("SQL", "Postgres");
        job.setSkills( "Python, MySQL");
        JobMatchResponseDTO jobMatchResponseDTO = jobMatchService.matchJob(resumeSkills, job);
        assertEquals(50,jobMatchResponseDTO.getMatchScore());
        assertEquals(List.of("MySQL"),jobMatchResponseDTO.getMatchingSkills());
        assertEquals(List.of("Python"),jobMatchResponseDTO.getMissingSkills());
    }

    @Test
    void analyzeResumeTest5() {
        Job job = new Job();
        List<String> resumeSkills = List.of("C++", "    ", "   OOPs");
        job.setSkills("C, C++");
        JobMatchResponseDTO jobMatchResponseDTO = jobMatchService.matchJob(resumeSkills, job);
        assertEquals(50,jobMatchResponseDTO.getMatchScore());
        assertEquals(List.of("C++"),jobMatchResponseDTO.getMatchingSkills());
        assertEquals(List.of("C"),jobMatchResponseDTO.getMissingSkills());
    }

    @Test
    void analyzeResumeTest6() {
        Job job = new Job();
        List<String> resumeSkills = Arrays.asList(null,"Java",null,"Spring Boot");
        job.setSkills("Java, SQL");
        JobMatchResponseDTO jobMatchResponseDTO = jobMatchService.matchJob(resumeSkills, job);
        assertEquals(50,jobMatchResponseDTO.getMatchScore());
        assertEquals(List.of("Java"),jobMatchResponseDTO.getMatchingSkills());
        assertEquals(List.of("SQL"),jobMatchResponseDTO.getMissingSkills());    }

    @Test
    void analyzeResumeTest7() {
        Job job = new Job();
        List<String> resumeSkills = Arrays.asList((String) null);
        job.setSkills("Java, SQL");
        JobMatchResponseDTO jobMatchResponseDTO = jobMatchService.matchJob(resumeSkills, job);
        assertEquals(0,jobMatchResponseDTO.getMatchScore());
        assertEquals(List.of(),jobMatchResponseDTO.getMatchingSkills());
        assertEquals(List.of("Java","SQL"),jobMatchResponseDTO.getMissingSkills());
    }

    @Test
    void analyzeResumeTest8() {
        Job job = new Job();
        List<String> resumeSkills = List.of("Java","MySQL");
        job.setSkills("Java,,MySQL");
        JobMatchResponseDTO jobMatchResponseDTO = jobMatchService.matchJob(resumeSkills, job);
        assertEquals(100,jobMatchResponseDTO.getMatchScore());
        assertEquals(List.of("Java","MySQL"),jobMatchResponseDTO.getMatchingSkills());
        assertEquals(List.of(),jobMatchResponseDTO.getMissingSkills());
    }

    @Test
    void analyzeResumeTest9() {
        Job job = new Job();
        List<String> resumeSkills = List.of("Java","MySQL");
        job.setSkills(null);
        JobMatchResponseDTO jobMatchResponseDTO = jobMatchService.matchJob(resumeSkills, job);
        assertEquals(0,jobMatchResponseDTO.getMatchScore());
        assertEquals(List.of(),jobMatchResponseDTO.getMatchingSkills());
        assertEquals(List.of(),jobMatchResponseDTO.getMissingSkills());
    }
}