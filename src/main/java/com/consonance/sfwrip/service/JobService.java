package com.consonance.sfwrip.service;

import com.consonance.sfwrip.domain.Job;

import java.util.List;

public interface JobService {
    List<Job> findAllByStatus(Integer status);
    List<Job> findByNameAndStatus(String name, Integer status);
    List<Job> findByAddressAndStatus(String address, Integer status);
    Job findByJobId(String jobId);
    void deleteByJobId(String jobId);
    Job addJob(Job job);
    void updateJob(Job job);
    void updateStatus(String jobId, Integer status);
    void updateSalary(String jobId, Integer salary);
    Integer addApplyNum(String jobId);
    Integer addPassNum(String jobId);
    Integer subtractApplyNum(String jobId);
    Integer subtractPassNum(String jobId);
}
