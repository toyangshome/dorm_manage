package com.newyang.dormmanage.controller;

import com.newyang.dormmanage.dao.StudentRepository;
import com.newyang.dormmanage.domain.model.Student;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/15 17:01
 */

@RestController
@RequestMapping("student")
@Api("学生API")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @GetMapping("list")
    public Page<Student> list() {
        Page<Student> all = studentRepository.findAll(Pageable.ofSize(4).withPage(1));
        return all;
    }
}
