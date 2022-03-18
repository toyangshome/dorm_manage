package com.newyang.dormmanage.auth.handler;

import com.newyang.dormmanage.auth.Role;
import com.newyang.dormmanage.auth.annotation.RequireUser;
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

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/17 20:37
 */
@Component
@Aspect
@Slf4j
public class RequireUserAnnotationHandler {
    // Controller 层切点
    @Pointcut("within(com.newyang.dormmanage.controller..*))")
    public void controllerPointCut () {
    }

    @Before("controllerPointCut()")
    public void authInterceptor (JoinPoint point) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession(true);
        MethodSignature signature = (MethodSignature) point.getSignature();
        RequireUser annotation = signature.getMethod().getAnnotation(RequireUser.class);
        if (annotation == null) {
            return;
        }
        Role role = (Role) session.getAttribute("role");
        if (role == null) {
            throw new AuthException("没有认证，不能进行访问");
        }
    }
}
