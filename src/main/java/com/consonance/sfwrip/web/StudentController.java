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

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public ResponseEntity<?> addStudent(@RequestBody Student student, UriComponentsBuilder uriComponentsBuilder) {
        studentService.addStudent(student);
        return ResponseEntity.created(uriComponentsBuilder.path("/{id}")
                .buildAndExpand(student.getStudentId())
                .toUri())
                .build();
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

    @RequestMapping(value = "/student/{studentId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudent(@PathVariable String studentId) {
        studentService.deleteByStudentId(studentId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/student/{studentId}/password", method = RequestMethod.PUT)
    public ResponseEntity<?> setPassword(@PathVariable String studentId,
                                         @RequestParam String originPassword,
                                         @RequestParam String newPassword) {
        studentService.setPassword(studentId, originPassword, newPassword);
        return ResponseEntity.noContent().build();
    }
}

