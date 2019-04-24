package com.consonance.sfwrip.web;

import com.consonance.sfwrip.domain.Student;
import com.consonance.sfwrip.domain.StudentToJob;
import com.consonance.sfwrip.domain.StudentToScholarship;
import com.consonance.sfwrip.service.StudentService;
import com.consonance.sfwrip.service.StudentToJobService;
import com.consonance.sfwrip.service.StudentToScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;
    private final StudentToScholarshipService studentToScholarshipService;
    private final StudentToJobService studentToJobService;

    @Autowired
    public StudentController(StudentService studentService, StudentToScholarshipService studentToScholarshipService, StudentToJobService studentToJobService) {
        this.studentService = studentService;
        this.studentToScholarshipService = studentToScholarshipService;
        this.studentToJobService = studentToJobService;
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

    @RequestMapping(value = "/student/{studentId}/scholarship", method = RequestMethod.GET)
    public List<StudentToScholarship> getStudentToScholarship(@PathVariable String studentId) {
        return studentToScholarshipService.findByStudentId(studentId);
    }

    @RequestMapping(value = "/student/{studentId}/scholarship", method = RequestMethod.POST)
    public ResponseEntity<?> addStudentToScholarship(@PathVariable String studentId, @RequestBody StudentToScholarship studentToScholarship, UriComponentsBuilder uriComponentsBuilder) {
        studentToScholarship.setStatus(0);
        studentToScholarshipService.addStudentToScholarship(studentToScholarship);
        return ResponseEntity.created(uriComponentsBuilder.path("/student/{id}").buildAndExpand(studentId).toUri()).build();

    }

    @RequestMapping(value = "/student/{studentId}/scholarship/{scholarshipId}", method = RequestMethod.GET)
    public StudentToScholarship getStudentToScholarshipByScholarshipId(@PathVariable String studentId, @PathVariable String scholarshipId) {
        return studentToScholarshipService.findByStudentIdAndScholarshipId(studentId, scholarshipId);
    }

    @RequestMapping(value = "/student/{studentId}/scholarship/{scholarshipId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudentToScholarshipByScholarshipId(@PathVariable String studentId, @PathVariable String scholarshipId) {
        studentToScholarshipService.deleteStudentToScholarshipByStudentIdAndScholarshipId(studentId, scholarshipId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/student/{studentId}/job", method = RequestMethod.GET)
    public List<StudentToJob> getStudentToJob(@PathVariable String studentId) {
        return studentToJobService.findByStudentId(studentId);
    }

    @RequestMapping(value = "/student/{studentId}/job", method = RequestMethod.POST)
    public ResponseEntity<?> addStudentToJob(@PathVariable String studentId, @RequestBody StudentToJob studentToJob, UriComponentsBuilder uriComponentsBuilder){
        studentToJob.setStatus(0);
        studentToJobService.addStudentToJob(studentToJob);
        return ResponseEntity.created(uriComponentsBuilder.path("student/{id}").buildAndExpand(studentId).toUri()).build();
    }

    @RequestMapping(value = "student/{studentId}/job/{jobId}", method = RequestMethod.GET)
    public StudentToJob getStudentToJobByJobId(@PathVariable String studentId, @PathVariable String jobId) {
        return studentToJobService.findByStudentIdAndJobId(studentId, jobId);
    }

    @RequestMapping(value = "student/{studentId}/job/{jobId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudentToJobByScholarshipId(@PathVariable String studentId, @PathVariable String jobId) {
        studentToJobService.deleteStudentToJobByStudentIdAndJobId(studentId, jobId);
        return ResponseEntity.noContent().build();
    }


}

