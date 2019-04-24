package com.consonance.sfwrip.service.impl;

import com.consonance.sfwrip.bean.WebResponse;
import com.consonance.sfwrip.domain.Job;
import com.consonance.sfwrip.domain.StudentToJob;
import com.consonance.sfwrip.exception.NotFoundException;
import com.consonance.sfwrip.repository.JobRepository;
import com.consonance.sfwrip.repository.StudentToJobRepository;
import com.consonance.sfwrip.service.StudentToJobService;
import com.consonance.sfwrip.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class StudentToJobServiceImpl implements StudentToJobService {
    private final JobRepository jobRepository;
    private final StudentToJobRepository studentToJobRepository;

    @Autowired
    public StudentToJobServiceImpl(JobRepository jobRepository, StudentToJobRepository studentToJobRepository) {
        this.jobRepository = jobRepository;
        this.studentToJobRepository = studentToJobRepository;
    }

    @Override
    public List<StudentToJob> findByStudentId(String studentId) {
        return studentToJobRepository.findAllByStudentId(studentId).stream().collect(Collectors.toList());
    }

    @Override
    public List<StudentToJob> findByJobId(String jobId) {
        return studentToJobRepository.findAllByJobId(jobId).stream().collect(Collectors.toList());
    }

    @Override
    public List<StudentToJob> findByStatus(Integer status) {
        return studentToJobRepository.findAllByStatus(status).stream().collect(Collectors.toList());
    }

    @Override
    public StudentToJob findByStudentIdAndJobId(String studentId, String jobId) {
        return Utilities.fetch(() -> studentToJobRepository.findAllByStudentIdAndJobId(studentId, jobId));
    }

    @Override
    public List<StudentToJob> findByJobIdAndStatus(String jobId, Integer status) {
        return studentToJobRepository.findAllByJobIdAndStatus(jobId, status).stream().collect(Collectors.toList());
    }

    @Override
    public StudentToJob addStudentToJob(StudentToJob studentToJob) {
        Optional<Job> job = jobRepository.findByJobId(studentToJob.getJobId());
        if (!job.isPresent()){
            throw new NotFoundException("job not exist");
        }
        CompletableFuture.runAsync(() -> {
            studentToJobRepository.save(studentToJob);
        });
        return studentToJobRepository.save(studentToJob);
    }

    @Override
    public void deleteStudentToJobByStudentIdAndJobId(String studentId, String jobId) {
        studentToJobRepository.deleteByStudentIdAndJobId(studentId, jobId);
    }

    @Override
    @Transactional
    public void updateStatus(String studentId, String jobId, Integer status) {
        StudentToJob studentToJob = Utilities.fetch(() -> studentToJobRepository.findAllByStudentIdAndJobId(studentId, jobId));
        studentToJob.setStatus(status);
        studentToJobRepository.findAllByStudentIdAndJobId(studentToJob.getStudentId(), studentToJob.getJobId())
                .ifPresent(stu -> Utilities.copyProperties(stu, stu));
    }
}
