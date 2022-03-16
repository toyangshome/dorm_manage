package com.newyang.dormmanage.service;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.dto.StudentUpdateDTO;
import com.newyang.dormmanage.domain.model.Student;
import com.newyang.dormmanage.domain.vo.StudentListVO;
import com.newyang.dormmanage.domain.vo.StudentVO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface StudentService {
    Page<StudentListVO> list (PageRequest pageRequest, Example<Student> example);

    void delete (Integer studentId);

    Response<StudentVO> update (StudentUpdateDTO student);
}
