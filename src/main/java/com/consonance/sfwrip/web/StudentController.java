package com.consonance.sfwrip.web;

import com.consonance.sfwrip.domain.Student;
import com.consonance.sfwrip.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/student/{studentId}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable String studentId) {
        return studentService.findStudentByStudentId(studentId);
    }

    @RequestMapping(value = "/student/{studentId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStudent(@PathVariable String studentId,
                                           @RequestBody Student student) {
        if (!student.getStudentId().equals(studentId)) {
            return ResponseEntity.badRequest().build();
        }
        studentService.updateStudent(student);
        return ResponseEntity.noContent().build();
    }


}

