package com.newyang.dormmanage.service.impl;

import com.newyang.dormmanage.commons.ResStatus;
import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.dao.AdminRepository;
import com.newyang.dormmanage.dao.DormManagerRepository;
import com.newyang.dormmanage.dao.StudentRepository;
import com.newyang.dormmanage.domain.model.Admin;
import com.newyang.dormmanage.domain.model.DormManager;
import com.newyang.dormmanage.domain.model.Student;
import com.newyang.dormmanage.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 0:28
 */

@Service
public class PasswordServiceImpl implements PasswordService {
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final DormManagerRepository dormManagerRepository;

    @Autowired
    public PasswordServiceImpl (AdminRepository adminRepository,
                                StudentRepository studentRepository,
                                DormManagerRepository dormManagerRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.dormManagerRepository = dormManagerRepository;
    }

    @Override
    public Response<Void> admin (Integer id, String newPwd, String oldPwd) {
        Optional<Admin> res = adminRepository.findById(id);
        if (res.isPresent()) {
            if (!oldPwd.equals(res.get().getPassword())) {
                return Response.failure(ResStatus.USER_PASSWORD_ERROR);
            } else {
                res.get().setPassword(newPwd);
                adminRepository.saveAndFlush(res.get());
            }
        }
        return Response.failure(ResStatus.USER_NOT_EXIST);
    }

    @Override
    public Response<Void> student (Integer id, String newPwd, String oldPwd) {
        Optional<Student> res = studentRepository.findById(id);
        if (res.isPresent()) {
            if (!oldPwd.equals(res.get().getPassword())) {
                return Response.failure(ResStatus.USER_PASSWORD_ERROR);
            } else {
                res.get().setPassword(newPwd);
                studentRepository.saveAndFlush(res.get());
            }
        }
        return Response.failure(ResStatus.USER_NOT_EXIST);
    }

    @Override
    public Response<Void> dormManager (Integer id, String newPwd, String oldPwd) {
        Optional<DormManager> res = dormManagerRepository.findById(id);
        if (res.isPresent()) {
            if (!oldPwd.equals(res.get().getPassword())) {
                return Response.failure(ResStatus.USER_PASSWORD_ERROR);
            } else {
                res.get().setPassword(newPwd);
                dormManagerRepository.saveAndFlush(res.get());
            }
        }
        return Response.failure(ResStatus.USER_NOT_EXIST);
    }
}
