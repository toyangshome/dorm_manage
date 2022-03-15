package com.newyang.dormmanage.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "t_dormmanager")
public class DormManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


}