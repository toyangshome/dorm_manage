package com.newyang.dormmanage.domain.model;

import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "t_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentId")
    private Integer studentId;

    @Column(name = "stuNum", length = 20)
    private String stuNum;

    @Column(name = "password", length = 20)
    private String password;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "dormName", length = 11)
    private String dormName;

    @Column(name = "sex", length = 10)
    private String sex;

    @Column(name = "tel", length = 15)
    private String tel;
    @Column(name= "dormBuildId")
    private Integer dormBuildId;

    public Integer getDormBuildId () {
        return dormBuildId;
    }

    public void setDormBuildId (Integer dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public String getTel () {
        return tel;
    }

    public void setTel (String tel) {
        this.tel = tel;
    }

    public String getSex () {
        return sex;
    }

    public void setSex (String sex) {
        this.sex = sex;
    }

    public String getDormName () {
        return dormName;
    }

    public void setDormName (String dormName) {
        this.dormName = dormName;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getStuNum () {
        return stuNum;
    }

    public void setStuNum (String stuNum) {
        this.stuNum = stuNum;
    }

    public Integer getId () {
        return studentId;
    }

    public void setId (Integer id) {
        this.studentId = id;
    }

}