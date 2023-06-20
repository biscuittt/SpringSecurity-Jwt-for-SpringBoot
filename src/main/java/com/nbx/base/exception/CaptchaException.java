package com.nbx.base.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author biscuitt
 * @version 1.0
 * @description 学成在线项目异常类
 * @date 2023/2/1 14:52
 */

public class CaptchaException extends AuthenticationException {

    private static final long serialVersionUID = 5565760508056698922L;

    private final String errMsg;

    public CaptchaException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    // 抛出指定枚举异常msg
    public static void cast(CommonError commonError){
        throw new CaptchaException(commonError.getErrMsg());
    }

    // 抛出异常，说明errMsg
    public static void cast(String errMsg){
        throw new CaptchaException(errMsg);
    }

}
