package com.consonance.sfwrip.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Job {
    @Id
    @GeneratedValue(generator = "juuid")
    @GenericGenerator(name = "juuid",strategy = "uuid")
    private String juuid;
    @Column(nullable = false, length = 30, unique = true)
    private String jobId;
    @Column(nullable = false, length = 100)
    private String name; //岗位名称
    @Column(nullable = false, length = 100)
    private String address; //工作地点
    @Column(nullable = false)
    private Integer type; //工作类型 0-长期岗位，1-临时岗位，2-假期岗位，3-其他
    @Column(nullable = false)
    private Integer applyNum; //申请人数
    @Column(nullable = false)
    private Integer passNum; //通过人数
    @Column(nullable = false)
    private Integer needNum; //需求人数
    @Column(nullable = false)
    private Integer salary; //薪资标准 /月
    @Column(nullable = false, length = 30)
    private String date; //工作时间
    @Column(nullable = false)
    private Integer status; //学工处审核状态 0-待审核，1-已审核, 2-未通过, 3-已满员

    public Job() {}

    public Job(String jobId, String name, String address, Integer type, Integer needNum, Integer salary, String date){
        this.jobId = jobId;
        this.name = name;
        this.address = address;
        this.type = type;
        this.needNum = needNum;
        this.salary = salary;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public Integer getNeedNum() {
        return needNum;
    }

    public Integer getPassNum() {
        return passNum;
    }

    public Integer getType() {
        return type;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNeedNum(Integer needNum) {
        this.needNum = needNum;
    }

    public void setPassNum(Integer passNum) {
        this.passNum = passNum;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setJuuid(String juuid) {
        this.juuid = juuid;
    }

    public String getJuuid() {
        return juuid;
    }
}
