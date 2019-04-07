package com.consonance.sfwrip.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student{

    @Id
    @GeneratedValue(generator = "suuid")
    @GenericGenerator(name = "suuid",strategy = "uuid")
    private String suuid;
    @Column(nullable = false, length = 10, unique = true)
    private String studentId; //学号
    @Column(nullable = false, length = 127)
    @JsonIgnore
    private String password; //密码
    @Column(nullable = false, length = 127)
    private String name; //姓名
    @Column(nullable = false)
    private Integer gender; // 性别 male-1, female-0
    @Column(nullable = true, length = 20)
    private String nation; //民族
    @Column(nullable = true, length = 30)
    private String birthday; //出生年月
    @Column(nullable = true, length = 30)
    private String status; //政治面貌
    @Column(nullable = true, length = 20, unique = true)
    private String idnum; //身份证号
    @Column(nullable = true, length = 30)
    private String sday; //入学年月
    @Column(nullable = true, length = 20)
    private String tel; //联系方式
    @Column(nullable = true)
    private Integer asys; //学制
    @Column(nullable = true, length = 100)
    private String college; //院系
    @Column(nullable = true, length = 100)
    private String profession; //专业
    @Column(nullable = true)
    private Integer plevel; //贫困等级 0-无，1-一般困难，2-困难，3-特别困难
    @Column(nullable = true)
    private Integer ptype; //贫困类型 0-无，1-低收入家庭，2-中等收入家庭
    @Column(nullable = true, length = 100)
    private String aname; //开户名
    @Column(nullable = true, length = 30)
    private String baccount; //银行账号
    @Column(nullable = true, length = 50)
    private String bank; //开户行

    @Column(nullable = true)
    private Integer faccount; //家庭户口 0-农村户口，1-城镇户口
    @Column(nullable = true)
    private Integer fnum; //家庭人数
    @Column(nullable = true)
    private Integer fincome; //家庭月收入
    @Column(nullable = true)
    private Integer pincome; //人均月收入
    @Column(nullable = true, length = 100)
    private String isource; //收入来源
    @Column(nullable = true)
    private Integer zcode; //家庭邮编
    @Column(nullable = true, length = 200)
    private String address; //家庭住址


    public Student(){}

    public Student(String studentId, String name, Integer gender) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentId() {return this.studentId;}

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getGender() {
        return gender;
    }

    public Integer getAsys() {
        return asys;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCollege() {
        return college;
    }

    public String getIdnum() {
        return idnum;
    }

    public String getName() {
        return name;
    }

    public String getNation() {
        return nation;
    }

    public String getPassword() {
        return password;
    }

    public Integer getPlevel() {
        return plevel;
    }

    public String getSday() {
        return sday;
    }

    public Integer getPtype() {
        return ptype;
    }

    public String getProfession() {
        return profession;
    }

    public String getStatus() {
        return status;
    }

    public String getSuuid() {
        return suuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setAsys(Integer asys) {
        this.asys = asys;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setPlevel(Integer plevel) {
        this.plevel = plevel;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setSday(String sday) {
        this.sday = sday;
    }

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSuuid(String suuid) {
        this.suuid = suuid;
    }

    public String getAname() {
        return aname;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getFaccount() {
        return faccount;
    }

    public Integer getFincome() {
        return fincome;
    }

    public Integer getFnum() {
        return fnum;
    }

    public Integer getPincome() {
        return pincome;
    }

    public Integer getZcode() {
        return zcode;
    }

    public String getAddress() {
        return address;
    }

    public String getBaccount() {
        return baccount;
    }
}
