package com.madhu.jobintelligenceplatformspringboot.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CorrectSkills implements SkillExtractor{
    @Override
    public List<String> extractSkills(String resumeText) {
        List<String> skills = new ArrayList<>();

        return skills;
    }
}
