package com.newyang.dormmanage.service;

import com.newyang.dormmanage.commons.Response;

public interface PasswordService {
    Response<Void> admin(Integer id, String newPwd,String oldPwd);
    Response<Void> student(Integer id, String newPwd,String oldPwd);
    Response<Void> dormManager(Integer id,String newPwd,String oldPwd);

}
