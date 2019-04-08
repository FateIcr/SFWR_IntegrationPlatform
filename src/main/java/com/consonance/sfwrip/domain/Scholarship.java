package com.consonance.sfwrip.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Scholarship {
    @Id
    @GeneratedValue(generator = "ssuuid")
    @GenericGenerator(name = "ssuuid",strategy = "uuid")
    private String ssuuid;
    @Column(nullable = false, length = 30, unique = true)
    private String scholarshipId; //奖学金ID
    @Column(nullable = false, length = 127)
    private String name; //奖学金名称
    @Column(nullable = false)
    private Integer money; //奖学金金额

    public Scholarship() {}

    public Scholarship(String scholarshipId, String name, Integer money) {
        this.money = money;
        this.name = name;
        this.scholarshipId = scholarshipId;
    }

    public String getName() {
        return name;
    }

    public Integer getMoney() {
        return money;
    }

    public String getScholarshipId() {
        return scholarshipId;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
