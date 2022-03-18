package com.newyang.dormmanage.controller;

import com.newyang.dormmanage.auth.Role;
import com.newyang.dormmanage.auth.annotation.RequireUser;
import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.dto.ChangePwdDTO;
import com.newyang.dormmanage.domain.dto.LoginDTO;
import com.newyang.dormmanage.domain.vo.LoginVO;
import com.newyang.dormmanage.service.LoginService;
import com.newyang.dormmanage.service.PasswordService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

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

    @GetMapping("info")
    @RequireUser
    public Response<LoginVO> getUserInfo (HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        return Response.success(new LoginVO(session.getAttribute("user"), ((Role) session.getAttribute("role")).ordinal()));
    }

    @GetMapping("quit")
    @RequireUser
    public Response<Void> quit (HttpServletRequest request) {
        request.getSession().invalidate();
        return Response.success();
    }

    @PostMapping("login")
    public Response<LoginVO> login (@RequestBody @Validated LoginDTO params, HttpServletRequest request) {
        Response res = null;
        switch (params.getRole()) {
            case 0:
                res = loginService.student(params.getUsername(), params.getPassword());
                break;
            case 1:
                res = loginService.dormManager(params.getUsername(), params.getPassword());
                break;
            case 2:
                res = loginService.admin(params.getUsername(), params.getPassword());
                break;
            default:
                res = Response.failure(- 1, "未知错误");
        }
        if (res.getCode() == 200) {
            HttpSession session = request.getSession();
            Role role = Arrays.stream(Role.values())
                    .filter(item -> item.ordinal() == params.getRole()).findFirst().get();
            session.setAttribute("role", role);
            session.setAttribute("user", res.getData());
            return Response.success(new LoginVO(res.getData(), role.ordinal()));
        }
        return res;
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
