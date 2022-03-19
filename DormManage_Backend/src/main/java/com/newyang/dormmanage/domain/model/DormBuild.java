package com.newyang.dormmanage.domain.model;

import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_dormbuild")
public class DormBuild implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dormBuildId")
    private Integer dormBuildId;
    @Column(name = "dormBuildName")
    private String dormBuildName;
    @Column(name = "dormBuildDetail")
    private String detail;


    public DormBuild () {

    }

    public DormBuild (String dormBuildName, String detail) {
        this.dormBuildName = dormBuildName;
        this.detail = detail;
    }


    public int getId () {
        return dormBuildId;
    }

    public void setId (int dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public String getDormBuildName () {
        return dormBuildName;
    }

    public void setDormBuildName (String dormBuildName) {
        this.dormBuildName = dormBuildName;
    }

    public String getDetail () {
        return detail;
    }

    public void setDetail (String detail) {
        this.detail = detail;
    }


}
