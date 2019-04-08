package com.consonance.sfwrip.service;

import com.consonance.sfwrip.domain.Scholarship;

import java.util.List;

public interface ScholarshipService {
    List<Scholarship> findAll();
    Scholarship findByScholarshipId(String scholarshipId);
    void deleteByScholarshipId(String scholarshipId);
    Scholarship addScholarship(Scholarship scholarship);
    void updateScholarship(Scholarship scholarship);
}
