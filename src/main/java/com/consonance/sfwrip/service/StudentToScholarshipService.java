package com.consonance.sfwrip.service;

import com.consonance.sfwrip.domain.StudentToScholarship;

import java.util.List;
import java.util.Optional;

public interface StudentToScholarshipService{
    List<StudentToScholarship> findByStudentId(String studentId);
    List<StudentToScholarship> findByScholarshipId(String scholarshipId);
    List<StudentToScholarship> findByStatus(Integer status);
    StudentToScholarship findByStudentIdAndScholarshipId(String studentId, String scholarshipId);
    List<StudentToScholarship> findByScholarshipIdAndStatus(String scholarshipId, Integer status);
    StudentToScholarship addStudentToScholarship(StudentToScholarship studentToScholarship);
    void deleteStudentToScholarshipByStudentIdAndScholarshipId(String studentId, String scholarshipId);
    void updateStatus(String studentId, String scholarshipId, Integer status);
}
