package com.newyang.dormmanage.domain.model;

import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@ToString
@Table(name = "t_record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "recordId", nullable = false)
    private Integer recordId;

    @Column(name = "studentNumber", length = 20)
    private String studentNumber;

    @Column(name = "studentName", length = 30)
    private String studentName;

    @Column(name = "dormBuildId")
    private Integer dormBuildId;

    @Column(name = "dormName", length = 11)
    private String dormName;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "detail", length = 50)
    private String detail;

    public String getDetail () {
        return detail;
    }

    public void setDetail (String detail) {
        this.detail = detail;
    }

    public LocalDate getDate () {
        return date;
    }

    public void setDate (LocalDate date) {
        this.date = date;
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

    public String getStudentName () {
        return studentName;
    }

    public void setStudentName (String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber () {
        return studentNumber;
    }

    public void setStudentNumber (String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getId () {
        return recordId;
    }

    public void setId (Integer id) {
        this.recordId = id;
    }
}