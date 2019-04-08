package com.consonance.sfwrip.service;

import com.consonance.sfwrip.domain.User;

public interface UserService {
    User findByUserId(String userId);
    void deleteByUserId(String userId);
    void save(User user);
    void edit(User user);
    User addUser(User user);
    void updateUser(User user);
    void setPassword(String userId, String originPassword, String newPassword);
    Boolean verifyPassword(String userId, String password);
    Integer getFlag(String userId);
}
