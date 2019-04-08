package com.consonance.sfwrip.service.impl;

import com.consonance.sfwrip.domain.Student;
import com.consonance.sfwrip.repository.StudentRepository;
import com.consonance.sfwrip.service.StudentService;
import com.consonance.sfwrip.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.CompletableFuture;


@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findStudentByStudentId(String studentId) {
        return Utilities.fetch(() -> studentRepository.findByStudentId(studentId));
    }

    @Override
    public void deleteByStudentId(String studentId) {
        studentRepository.deleteByStudentId(studentId);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void edit(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student addStudent(Student student) {
        CompletableFuture.runAsync(() -> {
            studentRepository.save(student);
        });
        return studentRepository.save(student);
    }


    @Override
    @Transactional
    public void updateStudent(Student student) {
        studentRepository.findByStudentId(student.getStudentId())
                .ifPresent(stu -> Utilities.copyProperties(student, stu));
    }

}
