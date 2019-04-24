package com.consonance.sfwrip.service.impl;

import com.consonance.sfwrip.domain.Scholarship;
import com.consonance.sfwrip.domain.StudentToScholarship;
import com.consonance.sfwrip.repository.StudentToScholarshipRepository;
import com.consonance.sfwrip.service.StudentToScholarshipService;
import com.consonance.sfwrip.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class StudentToScholarshipServiceImpl implements StudentToScholarshipService {
    private final StudentToScholarshipRepository studentToScholarshipRepository;

    @Autowired
    public StudentToScholarshipServiceImpl(StudentToScholarshipRepository studentToScholarshipRepository) {
        this.studentToScholarshipRepository = studentToScholarshipRepository;
    }


    @Override
    public List<StudentToScholarship> findByStudentId(String studentId) {
        return studentToScholarshipRepository.findAllByStudentId(studentId).stream().collect(Collectors.toList());
    }

    @Override
    public List<StudentToScholarship> findByScholarshipId(String scholarshipId) {
        return studentToScholarshipRepository.findAllByScholarshipId(scholarshipId).stream().collect(Collectors.toList());
    }

    @Override
    public List<StudentToScholarship> findByStatus(Integer status) {
        return studentToScholarshipRepository.findAllByStatus(status).stream().collect(Collectors.toList());
    }

    @Override
    public StudentToScholarship findByStudentIdAndScholarshipId(String studentId, String scholarshipId) {
        return Utilities.fetch(() -> studentToScholarshipRepository.findAllByStudentIdAndScholarshipId(studentId, scholarshipId));
    }

    @Override
    public List<StudentToScholarship> findByScholarshipIdAndStatus(String scholarshipId, Integer status) {
        return studentToScholarshipRepository.findAllByScholarshipIdAndStatus(scholarshipId, status).stream().collect(Collectors.toList());
    }


    @Override
    public StudentToScholarship addStudentToScholarship(StudentToScholarship studentToScholarship) {
        CompletableFuture.runAsync(() -> {
            studentToScholarshipRepository.save(studentToScholarship);
        });
        return studentToScholarshipRepository.save(studentToScholarship);
    }

    @Override
    public void deleteStudentToScholarshipByStudentIdAndScholarshipId(String studentId, String scholarshipId) {
        studentToScholarshipRepository.deleteByStudentIdAndScholarshipId(studentId, scholarshipId);
    }

    @Override
    @Transactional
    public void updateStatus(String studentId, String scholarshipId, Integer status) {
        StudentToScholarship studentToScholarship = Utilities.fetch(() -> studentToScholarshipRepository.findAllByStudentIdAndScholarshipId(studentId, scholarshipId));
        studentToScholarship.setStatus(status);
        studentToScholarshipRepository.findAllByStudentIdAndScholarshipId(studentToScholarship.getStudentId(), studentToScholarship.getScholarshipId())
                .ifPresent(stu -> Utilities.copyProperties(stu, stu));
    }
}
