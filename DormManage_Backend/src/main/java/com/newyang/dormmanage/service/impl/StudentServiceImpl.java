package com.newyang.dormmanage.service.impl;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.dao.DormBuildRepository;
import com.newyang.dormmanage.dao.StudentRepository;
import com.newyang.dormmanage.domain.dto.StudentAddDTO;
import com.newyang.dormmanage.domain.dto.StudentUpdateDTO;
import com.newyang.dormmanage.domain.model.Student;
import com.newyang.dormmanage.domain.vo.StudentListVO;
import com.newyang.dormmanage.domain.vo.StudentVO;
import com.newyang.dormmanage.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 14:37
 */
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository stRepo;
    private final DormBuildRepository dbRepo;

    @Autowired
    public StudentServiceImpl (StudentRepository studentRepository,
                               DormBuildRepository dormBuildRepository) {
        stRepo = studentRepository;
        dbRepo = dormBuildRepository;
    }

    @Override
    public Page<StudentListVO> list (PageRequest pageRequest, Example<Student> example) {

        return stRepo
                .findAll(example, pageRequest)
                .map((student -> new StudentListVO()
                                .setStudentId(student.getId())
                                .setDormName(student.getDormName())
                                .setSex(student.getSex())
                                .setName(student.getName())
                                .setStuNum(student.getStuNum())
                                .setDormBuildId(student.getDormBuildId())
                                .setDormBuildName(dbRepo.queryDormBuildName(student.getDormBuildId()))
                                .setTel(student.getTel())
                        )
                );
    }

    @Override
    public void delete (Integer studentId) {
        stRepo.deleteById(studentId);
    }

    @Override
    public Response<StudentVO> update (StudentUpdateDTO newStudent) {
        Optional<Student> findStd = stRepo.findById(newStudent.getStudentId());
        if (findStd.isPresent()) {
            Student oldStd = findStd.get();
            BeanUtils.copyProperties(newStudent, oldStd);
            Student student = stRepo.saveAndFlush(oldStd);
            return Response.success(StudentVO.convertFromStudent(student));
        }
        return Response.failure(- 1, "目标不存在");
    }

    @Override
    public Response<StudentVO> add (StudentAddDTO params) {
        Student queryParams = new Student();
        queryParams.setStuNum(params.getStuNum());
        Optional<Student> res = stRepo.findOne(Example.of(queryParams));
        if (! res.isPresent()) {
            Student std = new Student();
            std.setName(params.getName());
            std.setStuNum(params.getStuNum());
            std.setSex(params.getSex());
            std.setDormBuildId(params.getDormBuildId());
            std.setTel(params.getTel());
            std.setPassword(params.getPassword());
            std.setDormName(params.getDormName());
            Student afterSave = stRepo.saveAndFlush(std);
            return Response.success(new StudentVO()
                    .setStuNum(afterSave.getStuNum())
                    .setStudentId(afterSave.getId())
                    .setSex(afterSave.getSex())
                    .setDormName(afterSave.getDormName())
                    .setTel(afterSave.getTel())
                    .setDormBuildId(afterSave.getDormBuildId())
                    .setName(afterSave.getName())
            );
        }
        return Response.failure(- 1, "学号已存在");
    }
}
