package com.consonance.sfwrip.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StudentToScholarship {
    @Id
    @GeneratedValue(generator = "stsuuid")
    @GenericGenerator(name = "stsuuid",strategy = "uuid")
    private String stsuuid;
    @Column(nullable = false, length = 10)
    private String studentId; //学号
    @Column(nullable = false, length = 20)
    private String name; //姓名
    @Column(nullable = false, length = 30)
    private String scholarshipId; //奖项编号
    @Column(nullable = false)
    private Integer status; //申请状态 0-待审核，1-已通过，2-未通过
    @Column(length = 1000)
    private String reason; //申请理由

    public StudentToScholarship() {}

    public StudentToScholarship(String studentId, String name, String scholarshipId){
        this.studentId = studentId;
        this.name = name;
        this.scholarshipId = scholarshipId;
    }

    public StudentToScholarship(String studentId, String name, String scholarshipId, String reason){
        this.studentId = studentId;
        this.name = name;
        this.scholarshipId = scholarshipId;
        this.reason = reason;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public String getScholarshipId() {
        return scholarshipId;
    }

    public void setScholarshipId(String scholarshipId) {
        this.scholarshipId = scholarshipId;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public String getReason() {
        return reason;
    }

}
