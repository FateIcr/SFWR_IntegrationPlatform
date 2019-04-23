package com.consonance.sfwrip.service.impl;

import com.consonance.sfwrip.domain.Job;
import com.consonance.sfwrip.repository.JobRepository;
import com.consonance.sfwrip.service.JobService;
import com.consonance.sfwrip.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAllByStatus(Integer status) {
        return jobRepository.findByStatus(status).stream().collect(Collectors.toList());
    }

    @Override
    public List<Job> findByNameAndStatus(String name, Integer status) {
        return jobRepository.findByNameAndStatus(name, status).stream().collect(Collectors.toList());
    }

    @Override
    public List<Job> findByAddressAndStatus(String address, Integer status) {
        return jobRepository.findByAddressAndStatus(address, status).stream().collect(Collectors.toList());
    }

    @Override
    public Job findByJobId(String jobId) {
        return Utilities.fetch(() -> jobRepository.findByJobId(jobId));
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void deleteByJobId(String jobId) {
        jobRepository.deleteByJobId(jobId);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public Job addJob(Job job) {
        job.setStatus(0);
        job.setApplyNum(0);
        job.setPassNum(0);
        CompletableFuture.runAsync(() -> {
            jobRepository.save(job);
        });
        return jobRepository.save(job);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void updateJob(Job job) {
        jobRepository.findByJobId(job.getJobId())
                .ifPresent(stu -> Utilities.copyProperties(job, stu));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void updateStatus(String jobId, Integer status) {
        Job job = Utilities.fetch(() -> jobRepository.findByJobId(jobId));
        job.setStatus(status);
        jobRepository.findByJobId(job.getJobId())
                .ifPresent(stu -> Utilities.copyProperties(stu, stu));
    }

    @Override
    @Transactional
    public void updateSalary(String jobId, Integer salary) {
        Job job = Utilities.fetch(() -> jobRepository.findByJobId(jobId));
        job.setSalary(salary);
        jobRepository.findByJobId(job.getJobId())
                .ifPresent(stu -> Utilities.copyProperties(stu, stu));
    }

    @Override
    @Transactional
    public Integer addApplyNum(String jobId) {
        Job job = Utilities.fetch(() -> jobRepository.findByJobId(jobId));
        Integer num = job.getApplyNum();
        ++num;
        job.setApplyNum(num);
        jobRepository.findByJobId(job.getJobId())
                .ifPresent(stu -> Utilities.copyProperties(stu, stu));
        return num;

    }

    @Override
    @Transactional
    public Integer addPassNum(String jobId) {
        Job job = Utilities.fetch(() -> jobRepository.findByJobId(jobId));
        Integer num = job.getPassNum();
        ++num;
        job.setPassNum(num);
        jobRepository.findByJobId(job.getJobId())
                .ifPresent(stu -> Utilities.copyProperties(stu, stu));
        return num;
    }

    @Override
    @Transactional
    public Integer subtractApplyNum(String jobId) {
        Job job = Utilities.fetch(() -> jobRepository.findByJobId(jobId));
        Integer num = job.getApplyNum();
        --num;
        job.setApplyNum(num);
        jobRepository.findByJobId(job.getJobId())
                .ifPresent(stu -> Utilities.copyProperties(stu, stu));
        return num;
    }

    @Override
    @Transactional
    public Integer subtractPassNum(String jobId) {
        Job job = Utilities.fetch(() -> jobRepository.findByJobId(jobId));
        Integer num = job.getPassNum();
        --num;
        job.setPassNum(num);
        jobRepository.findByJobId(job.getJobId())
                .ifPresent(stu -> Utilities.copyProperties(stu, stu));
        return num;
    }
}
