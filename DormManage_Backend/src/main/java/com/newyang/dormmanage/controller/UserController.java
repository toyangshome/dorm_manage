package com.newyang.dormmanage.controller;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.dto.ChangePwdDTO;
import com.newyang.dormmanage.domain.dto.LoginDTO;
import com.newyang.dormmanage.service.LoginService;
import com.newyang.dormmanage.service.PasswordService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/15 18:35
 */

@RestController
@RequestMapping("user")
@Api(tags = "用户基本操作API")
public class UserController {
    private final LoginService loginService;
    private final PasswordService passwordService;

    public UserController (LoginService loginService, PasswordService passwordService) {
        this.loginService = loginService;
        this.passwordService = passwordService;
    }

    @PostMapping("login")
    public Response login (@RequestBody @Validated LoginDTO params) {
        switch (params.getRole()) {
            case 0:
                return loginService.student(params.getUsername(), params.getPassword());
            case 1:
                return loginService.dormManager(params.getUsername(), params.getPassword());
            case 2:
                return loginService.admin(params.getUsername(), params.getPassword());
            default:
                return Response.failure(- 1, "未知错误");
        }
    }

    @PostMapping("change_password")
    public Response<Void> changePassword (@RequestBody @Validated ChangePwdDTO params) {
        switch (params.getRole()) {
            case 0:
                return passwordService.student(params.getId(), params.getNewPassword(), params.getOldPassword());
            case 1:
                return passwordService.dormManager(params.getId(), params.getNewPassword(), params.getOldPassword());
            case 2:
                return passwordService.admin(params.getId(), params.getNewPassword(), params.getOldPassword());
            default:
                return Response.failure(- 1, "未知错误");
        }

    }

}
