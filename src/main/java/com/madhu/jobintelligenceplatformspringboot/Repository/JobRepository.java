package com.madhu.jobintelligenceplatformspringboot.Repository;

import com.madhu.jobintelligenceplatformspringboot.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByCompany(String company);
    List<Job> findByLocation(String location);
    List<Job> findBySkillsContaining(String skills);
    List<Job> findByCompanyIn(List<String> Company);
}
