package com.consonance.sfwrip.repository;

import com.consonance.sfwrip.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, String> {
    List<Job> findAllByStatus(Integer status);
    List<Job> findByNameAndStatus(String name, Integer status);
    List<Job> findByAddressAndStatus(String address, Integer status);
    List<Job> findByStatus(Integer status);
    Optional<Job> findByJobId(String jobId);
    @Modifying
    @Transactional
    void deleteByJobId(String jobId);
}
