package com.consonance.sfwrip.web;

import com.consonance.sfwrip.domain.StudentToJob;
import com.consonance.sfwrip.service.StudentToJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentToJobController {
    private final StudentToJobService studentToJobService;

    @Autowired
    public StudentToJobController(StudentToJobService studentToJobService) {
        this.studentToJobService = studentToJobService;
    }

    @RequestMapping(value = "/studenttojob/{status}", method = RequestMethod.GET)
    public List<StudentToJob> getStudentToScholarshipByStatus(@PathVariable Integer status) {
        return studentToJobService.findByStatus(status);
    }

    @RequestMapping(value = "/studenttojob/{jobId}/{status}", method = RequestMethod.GET)
    public List<StudentToJob> getStudentToJobByJobIdAndStatus(@PathVariable String jobId, @PathVariable Integer status) {
        return studentToJobService.findByJobIdAndStatus(jobId, status);
    }

    @RequestMapping(value = "/studenttojob/{jobId}", method = RequestMethod.PUT)
    public void updateStudentToJobStatus(@PathVariable String jobId, @RequestParam String studentId, @RequestParam Integer status) {
        studentToJobService.updateStatus(studentId, jobId, status);
    }

}
