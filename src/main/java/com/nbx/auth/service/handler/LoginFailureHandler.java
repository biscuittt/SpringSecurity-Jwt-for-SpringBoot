package com.nbx.auth.service.handler;

import com.alibaba.fastjson.JSON;
import com.nbx.base.exception.CaptchaException;
import com.nbx.common.model.ResponseResult;
import com.nbx.common.utils.WebUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author biscuitt
 * @version 1.0
 * @description LoginFailure 登录失败处理逻辑 Handler
 * @date 2023/6/19 15:38
 */
@Component
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {

    // 定义出错认证失败原因：用户名密码错误
    String errMsg = "用户名或密码错误!";
    if (exception instanceof CaptchaException) {
      errMsg = ((CaptchaException) exception).getErrMsg();
    }
    WebUtils.renderString(response, HttpStatus.UNAUTHORIZED.value(), errMsg);
    log.debug("登录失败，原因：{}", errMsg);

  }
}
