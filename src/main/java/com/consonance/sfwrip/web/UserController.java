package com.consonance.sfwrip.web;

import com.consonance.sfwrip.domain.Student;
import com.consonance.sfwrip.domain.User;
import com.consonance.sfwrip.service.StudentService;
import com.consonance.sfwrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UserController {
    private final UserService userService;
    private final StudentService studentService;

    @Autowired
    public UserController(UserService userService, StudentService studentService) {
        this.userService = userService;
        this.studentService = studentService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {
        userService.addUser(user);
        String path = "";
        // TODO 加入其他flag类型
        if(user.getFlag() == 0){
            path = "/student/{id}";
            Student student = new Student(user.getUserId(), user.getName(), user.getGender());
            studentService.addStudent(student);
        }
        return ResponseEntity.created(uriComponentsBuilder.path(path)
                .buildAndExpand(user.getUserId())
                .toUri())
                .build();
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId) {
        return userService.findByUserId(userId);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable String userId,
                                           @RequestBody User user) {
        if (!user.getUserId().equals(userId)) {
            return ResponseEntity.badRequest().build();
        }
        userService.updateUser(user);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudent(@PathVariable String userId) {
        Integer flag = userService.getFlag(userId);
        userService.deleteByUserId(userId);
        // TODO 加入其他flag类型
        if(flag == 0){
            studentService.deleteByStudentId(userId);
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/user/{userId}/password", method = RequestMethod.PUT)
    public ResponseEntity<?> setPassword(@PathVariable String userId,
                                         @RequestParam String originPassword,
                                         @RequestParam String newPassword) {
        userService.setPassword(userId, originPassword, newPassword);
        return ResponseEntity.noContent().build();
    }
}
