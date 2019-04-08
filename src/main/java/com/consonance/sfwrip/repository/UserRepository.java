package com.consonance.sfwrip.repository;

import com.consonance.sfwrip.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userId);
    @Modifying
    @Transactional
    void deleteByUserId(String userId);
}
