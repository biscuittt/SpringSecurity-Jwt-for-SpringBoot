package com.nbx.auth.service.handler;

import com.alibaba.fastjson.JSON;
import com.nbx.auth.model.po.NbxUser;
import com.nbx.auth.service.NbxUserService;
import com.nbx.common.model.ResponseResult;
import com.nbx.common.utils.JwtUtils;
import com.nbx.common.utils.WebUtils;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author biscuitt
 * @version 1.0
 * @description LoginSuccess 登录成功处理逻辑 Handler
 * @date 2023/6/19 15:29
 */
@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

  @Resource
  JwtUtils jwtUtils;

  @Resource
  NbxUserService nbxUserService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    // 生成jwt，并放到请求头Header中
    Object principal = authentication.getPrincipal();
    String jwt = "";
    // 需要注意的是Authentication中的principal，存储的就是UserDetails
    // 从刚刚loadUserByUsername中拿到UserDetails，获取自定义封装的Username，生成jwt
    if (principal instanceof UserDetails) {
      // 获取username
      String username = ((UserDetails) principal).getUsername();
      // 获取authorityStrings
      List<String> authorityStrings = new ArrayList<>();
      Collection<? extends GrantedAuthority> authorities = ((UserDetails) principal).getAuthorities();
      for (GrantedAuthority authority : authorities) {
        authorityStrings.add(authority.getAuthority());
      }
      // 获取用户详细信息
      NbxUser recordNbxUser = recordNbxUser(username);

      // 生成jwt
      Map<String, Object> claims = new HashMap<>();
      claims.put("userInfo", recordNbxUser);
      claims.put("authorities", authorityStrings);
      jwt = jwtUtils.generateToken(username, claims);
    }
    response.setHeader(jwtUtils.getHeader(), jwt);

    WebUtils.renderString(response, HttpStatus.OK.value(), "登录成功！");
    log.debug("用户:{}登录，登录时间：{}，jwt:{}", authentication.getName(), LocalDateTime.now(), jwt);

  }

  private NbxUser recordNbxUser(String username){
    NbxUser user = nbxUserService.getUserByUsername(username);
    user.setPassword(null);
    user.setId(null);
    return user;
  }
}
