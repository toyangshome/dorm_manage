package com.newyang.dormmanage.service.impl;

import com.newyang.dormmanage.commons.ResStatus;
import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.dao.AdminRepository;
import com.newyang.dormmanage.dao.DormManagerRepository;
import com.newyang.dormmanage.dao.StudentRepository;
import com.newyang.dormmanage.domain.model.Admin;
import com.newyang.dormmanage.domain.model.DormManager;
import com.newyang.dormmanage.domain.model.Student;
import com.newyang.dormmanage.domain.vo.LoginVO;
import com.newyang.dormmanage.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 0:27
 */

@Service
public class LoginServiceImpl implements LoginService {
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final DormManagerRepository dormManagerRepository;

    @Autowired
    public LoginServiceImpl (AdminRepository adminRepository,
                             StudentRepository studentRepository,
                             DormManagerRepository dormManagerRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.dormManagerRepository = dormManagerRepository;
    }

    @Override
    public Response<LoginVO.Admin> admin (String username, String password) {
        Admin example = new Admin();
        example.setUserName(username);
        Optional<Admin> res = adminRepository.findOne(Example.of(example));
        if (res.isPresent()) {
            if (res.get().getPassword().equals(password)) {
                Admin admin = res.get();
                return Response.success(new LoginVO.Admin()
                        .setAdminId(admin.getId())
                        .setName(admin.getName())
                        .setSex(admin.getSex())
                        .setUsername(admin.getUserName())
                        .setTel(admin.getTel())
                );
            } else {
                return Response.failure(ResStatus.USER_PASSWORD_ERROR);
            }
        }
        return Response.failure(ResStatus.USER_NOT_EXIST);
    }

    @Override
    public Response<LoginVO.DormManager> dormManager (String username, String password) {
        DormManager example = new DormManager();
        example.setUserName(username);
        Optional<DormManager> res = dormManagerRepository.findOne(Example.of(example));
        if (res.isPresent()) {
            if (res.get().getPassword().equals(password)) {
                DormManager dormManager = res.get();
                return Response.success(new LoginVO.DormManager()
                        .setDormManId(dormManager.getId())
                        .setName(dormManager.getName())
                        .setSex(dormManager.getSex())
                        .setUserName(dormManager.getUserName())
                        .setTel(dormManager.getTel())
                        .setDormBuildName(dormManager.getDormBuild().getDormBuildName())
                );
            } else {
                return Response.failure(ResStatus.USER_PASSWORD_ERROR);
            }
        }
        return Response.failure(ResStatus.USER_NOT_EXIST);
    }

    @Override
    public Response<LoginVO.Student> student (String stuNum, String password) {
        Student example = new Student();
        example.setStuNum(stuNum);
        Optional<Student> res = studentRepository.findOne(Example.of(example));
        if (res.isPresent()) {
            if (res.get().getPassword().equals(password)) {
                Student student = res.get();
                return Response.success(new LoginVO.Student()
                        .setStudentId(student.getId())
                        .setName(student.getName())
                        .setSex(student.getSex())
                        .setTel(student.getTel())
                        .setDormBuildId(student.getDormBuild().getId())
                        .setDormBuildName(student.getDormBuild().getDormBuildName())
                        .setDormName(student.getDormName())
                        .setStuNum(student.getStuNum())
                );
            } else {
                return Response.failure(ResStatus.USER_PASSWORD_ERROR);
            }
        }
        return Response.failure(ResStatus.USER_NOT_EXIST);
    }
}
