package com.newyang.dormmanage.auth.handler;

import com.newyang.dormmanage.auth.Role;
import com.newyang.dormmanage.auth.annotation.RequireRoles;
import com.newyang.dormmanage.auth.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/17 20:37
 */

@Component
@Aspect
@Slf4j
public class RequireRolesAnnotationHandler {

    // Controller 层切点
    @Pointcut("within(com.newyang.dormmanage.controller..*))")
    public void controllerPointCut () {
    }

    @Before("controllerPointCut()")
    public void authInterceptor (JoinPoint point) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession(true);
        MethodSignature signature = (MethodSignature) point.getSignature();
        RequireRoles annotation = signature.getMethod().getAnnotation(RequireRoles.class);
        if (annotation == null) return;
        List<Role> roles = Arrays.asList(annotation.value());
        Role role = (Role) session.getAttribute("role");

        if (! roles.contains(role)) {
            throw new AuthException("没有权限访问");
        }
    }
}
