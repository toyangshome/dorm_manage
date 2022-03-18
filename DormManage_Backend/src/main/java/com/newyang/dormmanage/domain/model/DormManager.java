package com.newyang.dormmanage.domain.model;

import javax.persistence.*;

@Entity
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


    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "tel")
    private String tel;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dormBuildId", referencedColumnName = "dormBuildId", insertable = false, updatable = false, nullable = true)
    private DormBuild dormBuild;

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

    public DormBuild getDormBuild () {
        return dormBuild;
    }

    public void setDormBuild (DormBuild dormBuild) {
        this.dormBuild = dormBuild;
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