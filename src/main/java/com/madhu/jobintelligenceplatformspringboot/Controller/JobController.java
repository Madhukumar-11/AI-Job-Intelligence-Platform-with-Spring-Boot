package com.madhu.jobintelligenceplatformspringboot.Controller;

import com.madhu.jobintelligenceplatformspringboot.DTOs.JobRequestDTO;
import com.madhu.jobintelligenceplatformspringboot.DTOs.JobResponseDTO;
import com.madhu.jobintelligenceplatformspringboot.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping
    public List<JobResponseDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobResponseDTO getJobById(@PathVariable int id){
        return jobService.getJobById(id);
    }

    @GetMapping("/search")
    public List<JobResponseDTO> searchJobs(
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String skills
    ){
        if(company != null)
            return jobService.getJobsByCompany(company);
        else if(location != null)
            return jobService.getJobsByLocation(location);
        else if(skills != null)
            return jobService.getJobsBySkills(skills);
        else
            return jobService.getAllJobs();
    }

    @GetMapping("/search/companies")
    public List<JobResponseDTO> getJobByCompanies(@RequestParam List<String> Company){
        return jobService.getJobByCompanies(Company);
    }

    @PostMapping
    public JobResponseDTO createJobs(@RequestBody JobRequestDTO jobRequestDTO) {
        return jobService.createJob(jobRequestDTO);
    }

    @PutMapping("/{id}")
    public JobResponseDTO updateJob(@PathVariable int id,@RequestBody JobRequestDTO dto) {
        return jobService.updateJob(id,dto);
    }

    @DeleteMapping("/{id}")
    public String deleteJobs(@PathVariable int id) {
        jobService.deleteJob(id);
        return "Job with id " + id + " has been deleted Successfully";
    }
}
