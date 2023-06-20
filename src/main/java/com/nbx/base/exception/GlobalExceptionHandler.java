package com.nbx.base.exception;

import com.nbx.common.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author biscuitt
 * @version 1.0
 * @description 全局异常处理器
 * @date 2023/6/19 16:02
 */

@Slf4j
@RestControllerAdvice // 控制器增强，捕捉异常，并返回json格式
public class GlobalExceptionHandler {

    // 程序员自定义，捕获可预知异常 CaptchaException 验证码异常
    @ExceptionHandler(CaptchaException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult doCustomException(CaptchaException e) {

        log.error("【验证码异常】{}", e.getErrMsg(), e);
        return new ResponseResult(401, e.getErrMsg());
    }

}
