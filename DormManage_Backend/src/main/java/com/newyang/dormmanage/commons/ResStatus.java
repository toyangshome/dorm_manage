package com.newyang.dormmanage.commons;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 0:14
 */


public enum ResStatus {
    // 成功状态码
    SUCCESS(200, "成功"),
    // 未知错误码
    FAILURE(501, "操作失败"),
    // 参数错误
    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),
    // 用户错误
    USER_NOT_LOGGED_IN(2001, "用户未登录,无权访问当前页面,请先登录"),
    USER_LOGIN_ERROR(2002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003, "账号已被禁用"),
    USER_NOT_EXIST(2004, "用户不存在"),
    USER_HAS_EXISTED(2005, "用户已存在"),
    USER_PASSWORD_ERROR(2006, "密码错误"),
    USER_REGISTER_ERROR(2007, "注册失败"),
    USER_ACCOUNT_EXPIRED(2008, "账号已过期"),
    USER_UNAUTHENTICATED(2009,"账号未认证"),
    USER_EMAIL_USED_ERROR(2010,"邮箱已被使用"),
    USER_ILLEGAL_MULTIPLY(2011,"非法操作!"),
    USER_LOGOUT_ERROR(2012,"登出失败"),
    USER_UNAUTHORIZED(2013,"用户未授权"),
    // Add操作类Code
    ADD_SUCCESS(3001, "新增成功"),
    ADD_FAILURE(3002, "新增失败"),

    // Http Message Parse Error
    JSON_PARSE_ERROR(6001, "Message 解析错误"),
    //Modify code

    UPDATE_SUCCESS(4001, "修改成功！"),
    UPDATE_FAIL(4002, "修改失败！"),

    // Remove code
    REMOVE_SUCCESS(5001, "删除成功！"),
    REMOVE_FAIL(5002, "删除失败！");

    // illegal multiply

    private final Integer code;
    private final String message;

    ResStatus (Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code () {
        return this.code;
    }

    public String message () {
        return this.message;
    }
}
