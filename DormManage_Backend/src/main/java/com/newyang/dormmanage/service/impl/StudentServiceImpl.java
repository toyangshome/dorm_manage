package com.newyang.dormmanage.service.impl;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.dao.StudentRepository;
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

    @Autowired
    public StudentServiceImpl (StudentRepository studentRepository) {
        stRepo = studentRepository;
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
                                .setDormBuildId(1)
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
}
