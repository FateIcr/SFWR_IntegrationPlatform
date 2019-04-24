package com.consonance.sfwrip.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StudentToJob {
    @Id
    @GeneratedValue(generator = "stjuuid")
    @GenericGenerator(name = "stjuuid",strategy = "uuid")
    private String stjuuid;
    @Column(nullable = false, length = 10)
    private String studentId; //学号
    @Column(nullable = false, length = 20)
    private String name; //姓名
    @Column(nullable = false, length = 30)
    private String jobId; //岗位编号
    @Column(nullable = false)
    private Integer status; //申请状态 0-待审核，1-已通过，2-未通过
    @Column(length = 1000)
    private String reason; //申请理由

    public StudentToJob() {}

    public StudentToJob(String studentId, String name, String jobId) {
        this.studentId = studentId;
        this. name = name;
        this.jobId = jobId;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String scholarshipId) {
        this.jobId = scholarshipId;
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
