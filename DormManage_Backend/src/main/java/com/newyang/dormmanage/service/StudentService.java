package com.newyang.dormmanage.service;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.model.Student;
import com.newyang.dormmanage.domain.vo.StudentListVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface StudentService {
    Page<StudentListVO> list(PageRequest pageRequest);
    void delete(Integer studentId);
    Response<Void> update(Student student);
}
