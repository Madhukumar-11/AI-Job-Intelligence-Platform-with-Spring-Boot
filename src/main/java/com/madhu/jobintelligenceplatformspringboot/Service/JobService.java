package com.madhu.jobintelligenceplatformspringboot.Service;

import com.madhu.jobintelligenceplatformspringboot.DTOs.JobRequestDTO;
import com.madhu.jobintelligenceplatformspringboot.DTOs.JobResponseDTO;
import com.madhu.jobintelligenceplatformspringboot.Exception.JobNotFoundException;
import com.madhu.jobintelligenceplatformspringboot.Repository.JobRepository;
import com.madhu.jobintelligenceplatformspringboot.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    private JobResponseDTO convertToResponseDTO(Job job) {
        return new JobResponseDTO(
                job.getId(),
                job.getTitle(),
                job.getCompany(),
                job.getLocation(),
                job.getSkills(),
                job.getDescription(),
                job.getExperience(),
                job.getSalary(),
                job.getApplyUrl());
    }

    public List<JobResponseDTO> getAllJobs(){
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToResponseDTO).toList();
    }

    public JobResponseDTO getJobById(int id){
        Job job = jobRepository.findById(id)
                .orElseThrow(()-> new JobNotFoundException("Job not found with id "+id));
        return convertToResponseDTO(job);
    }

    public JobResponseDTO createJob(JobRequestDTO dto){
        Job job = new Job();
        job.setTitle(dto.getTitle());
        job.setCompany(dto.getCompany());
        job.setLocation(dto.getLocation());
        job.setSkills(dto.getSkills());
        job.setDescription(dto.getDescription());
        job.setExperience(dto.getExperience());
        job.setSalary(dto.getSalary());
        job.setApplyUrl(dto.getApplyUrl());

        Job savedJobs = jobRepository.save(job);
        return convertToResponseDTO(savedJobs);
    }

    public JobResponseDTO updateJob(int id, JobRequestDTO dto){
        Job job = jobRepository.findById(id).
                orElseThrow(() -> new JobNotFoundException("Job not found with id " + id));

        job.setTitle(dto.getTitle());
        job.setCompany(dto.getCompany());
        job.setLocation(dto.getLocation());
        job.setSkills(dto.getSkills());
        job.setDescription(dto.getDescription());
        job.setExperience(dto.getExperience());
        job.setSalary(dto.getSalary());
        job.setApplyUrl(dto.getApplyUrl());

        Job updatedJob = jobRepository.save(job);
        return convertToResponseDTO(updatedJob);
    }

    public List<JobResponseDTO> getJobsByCompany(String company){
        return jobRepository.findByCompany(company)
                .stream().map(this::convertToResponseDTO).toList();
    }

    public List<JobResponseDTO> getJobsByLocation(String location){
        return jobRepository.findByLocation(location)
                .stream().map(this::convertToResponseDTO).toList();
    }

    public List<JobResponseDTO> getJobsBySkills(String skills){
        return jobRepository.findBySkillsContaining(skills)
                .stream().map(this::convertToResponseDTO).toList();
    }

    public List<JobResponseDTO> getJobByCompanies(List<String> Company){
        return jobRepository.findByCompanyIn(Company)
                .stream().map(this::convertToResponseDTO).toList();
    }

    public void deleteJob(int id){
        if(!jobRepository.existsById(id)){
            throw new JobNotFoundException("Job not found with id " + id);
        }
        jobRepository.deleteById(id);
    }
}
