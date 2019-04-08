package com.consonance.sfwrip.web;

import com.consonance.sfwrip.domain.Scholarship;
import com.consonance.sfwrip.service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScholarshipController {
    private final ScholarshipService scholarshipService;

    @Autowired
    public ScholarshipController(ScholarshipService scholarshipService) {
        this.scholarshipService = scholarshipService;
    }

    @RequestMapping(value = "/scholarship", method = RequestMethod.POST)
    public void addScholarship(@RequestBody Scholarship scholarship) {
        scholarshipService.addScholarship(scholarship);
    }

    @RequestMapping(value = "/scholarship", method = RequestMethod.GET)
    public List<Scholarship> findAll() {
        return scholarshipService.findAll();
    }

    @RequestMapping(value = "/scholarship/{scholarshipId}", method = RequestMethod.GET)
    public Scholarship getScholarship(@PathVariable String scholarshipId) {
        return scholarshipService.findByScholarshipId(scholarshipId);
    }

    @RequestMapping(value = "/scholarship/{scholarshipId}", method = RequestMethod.PUT)
    public void updateScholarship(@PathVariable String scholarshipId, @RequestBody Scholarship scholarship) {
        if(scholarship.getScholarshipId().equals(scholarshipId))
            scholarshipService.updateScholarship(scholarship);
    }

    @RequestMapping(value = "/scholarship/{scholarshipId}", method = RequestMethod.DELETE)
    public void deleteScholarship(@PathVariable String scholarshipId) {
        scholarshipService.deleteByScholarshipId(scholarshipId);
    }
}
