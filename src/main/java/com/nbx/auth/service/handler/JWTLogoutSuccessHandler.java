package com.nbx.auth.service.handler;

import com.nbx.common.utils.JwtUtils;
import com.nbx.common.utils.WebUtils;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

/**
 * @author biscuitt
 * @version 1.0
 * @description TODO
 * @date 2023/6/20 16:10
 */

@Component
@Slf4j
public class JWTLogoutSuccessHandler implements LogoutSuccessHandler {

  @Autowired
  JwtUtils jwtUtils;

  @Override
  public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

    String username = "";

    if (authentication != null) {
      Object principal = authentication.getPrincipal();
      if (principal instanceof UserDetails) {
        username = ((UserDetails) principal).getUsername();
      }
      new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
    }

    httpServletResponse.setHeader(jwtUtils.getHeader(), "");
    WebUtils.renderString(httpServletResponse, HttpStatus.OK.value(), "退出登录成功！");
    log.debug("用户：{} 退出登录，退出时间：{}", username, LocalDateTime.now());

  }
}

