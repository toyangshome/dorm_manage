package com.newyang.dormmanage.service;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.vo.LoginVO;

public interface LoginService {
    Response<LoginVO.Admin> admin(String username, String password);
    Response<LoginVO.DormManager> dormManager(String username, String password);
    Response<LoginVO.Student> student(String stuNum,String password);
}
