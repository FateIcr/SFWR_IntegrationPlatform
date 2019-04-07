package com.consonance.sfwrip.service;

import com.consonance.sfwrip.domain.Student;

public interface StudentService {
    Student findStudentByStudentId(String studentId);
    void deleteByStudentId(String studentId);
    void save(Student student);
    void edit(Student student);
    Student addStudent(Student student);
    void updateStudent(Student student);
    void setPassword(String studentId, String originPassword, String newPassword);
}
