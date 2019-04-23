package com.consonance.sfwrip.service.impl;

import com.consonance.sfwrip.domain.Student;
import com.consonance.sfwrip.domain.User;
import com.consonance.sfwrip.repository.UserRepository;
import com.consonance.sfwrip.service.UserService;
import com.consonance.sfwrip.util.DefaultData;
import com.consonance.sfwrip.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUserId(String userId) {
        return Utilities.fetch(() -> userRepository.findByUserId(userId));
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void deleteByUserId(String userId) {
        userRepository.deleteByUserId(userId);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void edit(User user) {
        userRepository.save(user);
    }

    @Override
    public User addUser(User user) {
        String tempId = user.getUserId();
        if(exist(tempId)) {
            throw new RuntimeException("用户名已存在!");
        }
        CompletableFuture.runAsync(() -> {
            user.setPassword(DefaultData.defaultUserPassword);
            userRepository.save(user);
        });
        return userRepository.save(user);
    }

    @Override
    @Secured("ROLE_USER")
    public void updateUser(User user) {
        userRepository.findByUserId(user.getUserId())
                .ifPresent(stu -> Utilities.copyProperties(user, stu));
    }

    @Override
    @Transactional
    public void setPassword(String userId, String originPassword, String newPassword) {
        User user = Utilities.fetch(() -> userRepository.findByUserId(userId));
        // TODO 密码加密验证
        if (originPassword.equals(user.getPassword())) {
            // TODO 密码加密
            user.setPassword(newPassword);
        } else {
            throw new IllegalArgumentException("Wrong password");
        }
    }

    @Override
    public Boolean verifyPassword(String userId, String password) {
        Boolean result = false;
        User user = Utilities.fetch(() -> userRepository.findByUserId(userId));
        // TODO 密码加密验证
        if(password.equals(user.getPassword())){
            result = true;
        }

        return result;
    }

    @Override
    public Integer getFlag(String userId) {
        User user = Utilities.fetch(() -> userRepository.findByUserId(userId));
        return user.getFlag();
    }

    private boolean exist(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        return (user.isPresent());
    }

}
