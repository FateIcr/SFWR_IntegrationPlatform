package com.consonance.sfwrip.web;

import com.consonance.sfwrip.domain.Job;
import com.consonance.sfwrip.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @RequestMapping(value = "/joball/{status}", method = RequestMethod.GET)
    public List<Job> getJobByStatus(@PathVariable Integer status){
        return jobService.findAllByStatus(status);
    }

    @RequestMapping(value = "/job/{jobId}", method = RequestMethod.GET)
    public Job getJobByJobId(@PathVariable String jobId){
        return jobService.findByJobId(jobId);
    }

    @RequestMapping(value = "/job/{jobId}", method = RequestMethod.PUT)
    public void updateJobStatus(@PathVariable String jobId, @RequestParam Integer status){
        jobService.updateStatus(jobId, status);
    }

    @RequestMapping(value = "/job/{jobId}/salary", method = RequestMethod.PUT)
    public void updateJobSalary(@PathVariable String jobId, @RequestParam Integer salary){
        jobService.updateSalary(jobId, salary);
    }

    @RequestMapping(value = "/job", method = RequestMethod.POST)
    public void addJob(@RequestBody Job job){
        jobService.addJob(job);
    }
}
