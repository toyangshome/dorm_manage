package com.newyang.dormmanage.domain.model;

import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Table(name = "t_dormmanager")
public class DormManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dormManId", nullable = false)
    private Integer dormManId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "dormBuildId")
    private Integer dormBuildId;

    @Column(name = "dormBuildName")
    private String dormBuildName;
    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "tel")
    private String tel;

    public Integer getId () {
        return dormManId;
    }

    public void setId (Integer dormManId) {
        this.dormManId = dormManId;
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

    public Integer getDormBuildId () {
        return dormBuildId;
    }

    public void setDormBuildId (Integer dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public String getDormBuildName () {
        return dormBuildName;
    }

    public void setDormBuildName (String dormBuildName) {
        this.dormBuildName = dormBuildName;
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