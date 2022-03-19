package com.newyang.dormmanage.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "t_dorm")
public class Dorm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dormId", nullable = false)
    private Integer id;

    @Column(name = "dormBuildId")
    private Integer dormBuildId;

    @Column(name = "dormName", length = 20)
    private String dormName;

    @Column(name = "dormType", length = 20)
    private String dormType;

    @Column(name = "dormNumber")
    private Integer dormNumber;

    @Column(name = "dormTel", length = 20)
    private String dormTel;

    public String getDormTel () {
        return dormTel;
    }

    public void setDormTel (String dormTel) {
        this.dormTel = dormTel;
    }

    public Integer getDormNumber () {
        return dormNumber;
    }

    public void setDormNumber (Integer dormNumber) {
        this.dormNumber = dormNumber;
    }

    public String getDormType () {
        return dormType;
    }

    public void setDormType (String dormType) {
        this.dormType = dormType;
    }

    public String getDormName () {
        return dormName;
    }

    public void setDormName (String dormName) {
        this.dormName = dormName;
    }

    public Integer getDormBuildId () {
        return dormBuildId;
    }

    public void setDormBuildId (Integer dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }
}