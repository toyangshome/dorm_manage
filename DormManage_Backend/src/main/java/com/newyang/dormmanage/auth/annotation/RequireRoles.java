package com.newyang.dormmanage.auth.annotation;

import com.newyang.dormmanage.auth.Role;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireRoles {
    Role[] value () default {};
}
