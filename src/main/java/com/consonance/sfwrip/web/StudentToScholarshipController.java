package com.consonance.sfwrip.web;

import com.consonance.sfwrip.domain.StudentToScholarship;
import com.consonance.sfwrip.service.StudentToScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class StudentToScholarshipController {
    private final StudentToScholarshipService studentToScholarshipService;

    @Autowired
    public StudentToScholarshipController(StudentToScholarshipService studentToScholarshipService) {
        this.studentToScholarshipService = studentToScholarshipService;
    }

    @RequestMapping(value = "/studenttoscholarship/{status}", method = RequestMethod.GET)
    public List<StudentToScholarship> getStudentToScholarshipByStatus(@PathVariable Integer status) {
        return studentToScholarshipService.findByStatus(status);
    }

    @RequestMapping(value = "/studenttoscholarship/{scholarshipId}/{status}", method = RequestMethod.GET)
    public List<StudentToScholarship> getStudentToScholarshipByScholarshipIdAndStatus(@PathVariable String scholarshipId, @PathVariable Integer status) {
        return studentToScholarshipService.findByScholarshipIdAndStatus(scholarshipId, status);
    }

    @RequestMapping(value = "/studenttoscholarship/{scholarshipId}", method = RequestMethod.PUT)
    public void updateStudentToScholarshipStatus(@PathVariable String scholarshipId, @RequestParam String studentId, @RequestParam Integer status) {
        studentToScholarshipService.updateStatus(studentId, scholarshipId, status);
    }
}
