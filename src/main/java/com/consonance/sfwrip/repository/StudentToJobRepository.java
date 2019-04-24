package com.consonance.sfwrip.repository;

import com.consonance.sfwrip.domain.StudentToJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentToJobRepository extends JpaRepository<StudentToJob, String> {


    List<StudentToJob> findAllByStudentId(String studentId);
    List<StudentToJob> findAllByJobId(String jobId);
    Optional<StudentToJob> findAllByStudentIdAndJobId(String studentId, String jobId);
    List<StudentToJob> findAllByStatus(Integer status);
    List<StudentToJob> findAllByJobIdAndStatus(String jobId, Integer status);
    @Modifying
    @Transactional
    void deleteByStudentIdAndJobId(String studentId, String jobId);
}
