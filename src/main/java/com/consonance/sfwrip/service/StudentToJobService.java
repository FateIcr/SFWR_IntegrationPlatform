package com.consonance.sfwrip.service;

import com.consonance.sfwrip.domain.StudentToJob;

import java.util.List;

public interface StudentToJobService {
    List<StudentToJob> findByStudentId(String studentId);
    List<StudentToJob> findByJobId(String jobId);
    List<StudentToJob> findByStatus(Integer status);
    StudentToJob findByStudentIdAndJobId(String studentId, String jobId);
    List<StudentToJob> findByJobIdAndStatus(String jobId, Integer status);
    StudentToJob addStudentToJob(StudentToJob studentToJob);
    void deleteStudentToJobByStudentIdAndJobId(String studentId, String jobId);
    void updateStatus(String studentId, String jobId, Integer status);
}
