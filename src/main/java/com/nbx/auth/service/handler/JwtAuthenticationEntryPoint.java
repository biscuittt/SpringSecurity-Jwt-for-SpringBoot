package com.nbx.auth.service.handler;

import com.alibaba.fastjson.JSON;
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
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author biscuitt
 * @version 1.0
 * @description JwtAuthenticationFilter 失败后执行逻辑
 * @date 2023/6/20 15:49
 */
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


  @Override
  public void commence(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, AuthenticationException e)
      throws IOException, ServletException {

    WebUtils.renderString(httpServletResponse, HttpStatus.FORBIDDEN.value(), "请先登录");
    log.debug("jwt已失效或不存在，认证失败！");

  }
}
