package com.consonance.sfwrip.repository;

import com.consonance.sfwrip.domain.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ScholarshipRepository extends JpaRepository<Scholarship, String> {
    Optional<Scholarship> findByScholarshipId(String scholarshipId);
    List<Scholarship> findAll();
    @Modifying
    @Transactional
    void deleteByScholarshipId(String scholarshipId);
}
