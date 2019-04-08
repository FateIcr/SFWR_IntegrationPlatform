package com.consonance.sfwrip.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(generator = "uuuid")
    @GenericGenerator(name = "uuuid",strategy = "uuid")
    private String uuuid;
    @Column(nullable = false, length = 10, unique = true)
    private String userId; //账号，可为学号或工号
    @Column(nullable = false, length = 127)
    @JsonIgnore
    private String password; //密码
    @Column(nullable = false, length = 127)
    private String name; //姓名
    @Column(nullable = false)
    private Integer flag; //用户种类 0-学生
    @Column(nullable = false)
    private Integer gender; // 性别 male-1, female-0

    public User(){}

    public User(String userId, String name, Integer gender, Integer flag){
        this.userId = userId;
        this.name = name;
        this.flag = flag;
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }

    public Integer getGender() {
        return gender;
    }
}
