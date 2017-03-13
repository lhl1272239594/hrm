package com.jims.sys.security;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @author het
 * @desc …… Entity
 * @date 2015/5/18 0018 上午 10:55.
 */
public class ValidCodeException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public ValidCodeException() {
        super();
    }

    public ValidCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidCodeException(String message) {
        super(message);
    }

    public ValidCodeException(Throwable cause) {
        super(cause);
    }
}