package com.consonance.sfwrip.repository;

import com.consonance.sfwrip.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByStudentId(String studentId);
    @Modifying
    @Transactional
    void deleteByStudentId(String studentId);
}
