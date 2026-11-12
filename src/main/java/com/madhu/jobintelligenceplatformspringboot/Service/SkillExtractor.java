package com.madhu.jobintelligenceplatformspringboot.Service;

import java.util.List;

public interface SkillExtractor {
    List<String> extractSkills(String resumeText);
}
