package com.newyang.dormmanage.handler;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 10:33
 */

@Component
@Aspect
@Slf4j
public class LogAspect {

    // Controller 层切点
    @Pointcut("within(com.newyang.dormmanage.controller..*))")
    public void controllerPointCut () {
    }

    @Around("controllerPointCut()")
    public Object controllerAround (ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        try {
            //实现保存日志逻辑
            saveLog(point, time);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }


    private void saveLog (ProceedingJoinPoint joinPoint, long time) {

        // 获取方法的关键信息，类，包
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogEntity sysLogEntity = new SysLogEntity();
        sysLogEntity.setExecuteTime(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sysLogEntity.setCreateDate(dateFormat.format(new Date()));
        //请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLogEntity.setClassName(className);
        sysLogEntity.setMethodName(methodName);
        //请求的参数
        Object[] args = joinPoint.getArgs();
        // 请求的Ip
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        log.info("Request url: {}", request.getRequestURI());
        sysLogEntity.setRemoteAddr(request.getRemoteAddr());
        try {
            List<String> list = new ArrayList<>();
            for (Object o : args) {
                list.add(o.toString());
            }
            sysLogEntity.setParams(list.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("SysLog: {}", sysLogEntity);
    }
}