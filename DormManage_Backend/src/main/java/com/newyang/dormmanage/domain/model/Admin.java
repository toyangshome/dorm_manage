package com.newyang.dormmanage.domain.model;

import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "t_admin")
@ToString
public class Admin {

    @Id
    @Column(name = "adminId",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer adminId;

    @Column(name = "userName")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "sex")
    private String sex;
    @Column(name = "tel")
    private String tel;

    public Admin () {

    }


    public Admin (String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    public int getId () {
        return adminId;
    }

    public void setId (int adminId) {
        this.adminId = adminId;
    }

    public String getUserName () {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getSex () {
        return sex;
    }

    public void setSex (String sex) {
        this.sex = sex;
    }

    public String getTel () {
        return tel;
    }

    public void setTel (String tel) {
        this.tel = tel;
    }


}
