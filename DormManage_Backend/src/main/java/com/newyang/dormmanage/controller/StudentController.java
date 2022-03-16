package com.newyang.dormmanage.controller;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.dto.PageDTO;
import com.newyang.dormmanage.domain.dto.StudentAddDTO;
import com.newyang.dormmanage.domain.dto.StudentListDTO;
import com.newyang.dormmanage.domain.dto.StudentUpdateDTO;
import com.newyang.dormmanage.domain.model.Student;
import com.newyang.dormmanage.domain.vo.StudentListVO;
import com.newyang.dormmanage.domain.vo.StudentVO;
import com.newyang.dormmanage.service.StudentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/15 17:01
 */

@RestController
@RequestMapping("student")
@Api(tags = "学生API")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController (StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("list")
    public Response<Page<StudentListVO>> list (@RequestBody @Validated StudentListDTO params) {
        PageDTO page = params.getPage();
        PageRequest pageRequest = PageRequest.of(page.getCurrent(), page.getPageSize());
        Student exampleQuery = new Student();
        exampleQuery.setDormName(params.getDormBuildName());
        exampleQuery.setName(params.getStudentName());
        Example<Student> example = Example.of(exampleQuery);
        Page<StudentListVO> queryRes = studentService.list(pageRequest, example);
        return Response.success(queryRes);
    }

    @PostMapping("delete/{id}")
    public Response<Void> delete (@PathVariable("id") Integer id) {
        studentService.delete(id);
        return Response.success();
    }

    @PostMapping("update")
    public Response<StudentVO> update (@RequestBody @Validated StudentUpdateDTO params) {
        return studentService.update(params);
    }

    @PostMapping("add")
    public Response<Void> add (@RequestBody @Validated StudentAddDTO params) {
        return Response.success();
    }
}
