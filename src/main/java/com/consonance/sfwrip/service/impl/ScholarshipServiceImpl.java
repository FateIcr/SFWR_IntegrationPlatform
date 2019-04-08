package com.consonance.sfwrip.service.impl;

import com.consonance.sfwrip.domain.Scholarship;
import com.consonance.sfwrip.repository.ScholarshipRepository;
import com.consonance.sfwrip.service.ScholarshipService;
import com.consonance.sfwrip.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ScholarshipServiceImpl implements ScholarshipService {
    private final ScholarshipRepository scholarshipRepository;

    @Autowired
    public ScholarshipServiceImpl(ScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository;
    }

    @Override
    public List<Scholarship> findAll() {
        return scholarshipRepository.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public Scholarship findByScholarshipId(String scholarshipId) {
        return Utilities.fetch(() -> scholarshipRepository.findByScholarshipId(scholarshipId));
    }

    @Override
    public void deleteByScholarshipId(String scholarshipId) {
        scholarshipRepository.deleteByScholarshipId(scholarshipId);
    }

    @Override
    public Scholarship addScholarship(Scholarship scholarship) {
        CompletableFuture.runAsync(() -> {
            scholarshipRepository.save(scholarship);
        });
        return scholarshipRepository.save(scholarship);
    }

    @Override
    @Transactional
    public void updateScholarship(Scholarship scholarship) {
        scholarshipRepository.findByScholarshipId(scholarship.getScholarshipId())
                .ifPresent(stu -> Utilities.copyProperties(scholarship, stu));
    }
}
