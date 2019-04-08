package com.consonance.sfwrip.repository;

import com.consonance.sfwrip.domain.StudentToScholarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentToScholarshipRepository extends JpaRepository<StudentToScholarship, String> {
    List<StudentToScholarship> findAllByStudentId(String studentId);
    List<StudentToScholarship> findAllByScholarshipId(String scholarshipId);
    Optional<StudentToScholarship> findAllByStudentIdAndScholarshipId(String studentId, String scholarshipId);
    List<StudentToScholarship> findAllByStatus(Integer status);
    List<StudentToScholarship> findAllByScholarshipIdAndStatus(String scholarshipId, Integer status);
    @Modifying
    @Transactional
    void deleteByStudentIdAndScholarshipId(String studentId, String scholarshipId);
}
