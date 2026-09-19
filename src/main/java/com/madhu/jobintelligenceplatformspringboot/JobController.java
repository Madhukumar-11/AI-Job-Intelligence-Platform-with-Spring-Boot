package com.madhu.jobintelligenceplatformspringboot;

import com.madhu.jobintelligenceplatformspringboot.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private  JobService jobService;

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
            @RequestParam(required = false) String Company,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String skills
    ){
        if(Company != null)
            return jobService.getJobsByCompany(Company);
        else if(location != null)
            return jobService.getJobsByCompany(location);
        else if(skills != null)
            return jobService.getJobsByCompany(skills);
        else
            return jobService.getAllJobs();
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
