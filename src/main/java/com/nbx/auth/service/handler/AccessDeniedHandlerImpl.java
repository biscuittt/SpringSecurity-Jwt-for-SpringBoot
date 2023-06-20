package com.nbx.auth.service.handler;

import com.alibaba.fastjson.JSON;
import com.nbx.common.model.ResponseResult;
import com.nbx.common.utils.WebUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * @author biscuitt
 * @version 1.0
 * @description 接口权限不足走的 Handler
 * @date 2023/6/15 16:03
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
                AccessDeniedException e) throws IOException, ServletException {

    WebUtils.renderString(response, HttpStatus.FORBIDDEN.value(), e.getMessage());
  }
}
