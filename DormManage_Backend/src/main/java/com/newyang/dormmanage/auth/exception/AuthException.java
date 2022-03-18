package com.newyang.dormmanage.auth.exception;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/17 21:08
 */

public class AuthException extends RuntimeException {
    public AuthException () {
        super();
    }
    public AuthException (String message) {
        super(message);
    }
    public AuthException (String message, Throwable cause) {
        super(message, cause);
    }
    public AuthException (Throwable cause) {
        super(cause);
    }
    protected AuthException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
