package com.nbx.auth.service.filter;

import com.nbx.auth.service.impl.UserServiceImpl;
import com.nbx.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author biscuitt
 * @version 1.0
 * @description Jwt认证过滤器
 * @date 2023/6/19 16:27
 */
@Component
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  UserServiceImpl userDetailService;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

    String jwt = request.getHeader(jwtUtils.getHeader());

    // 这里如果没有jwt，继续往后走，因为后面还有鉴权管理器等去判断是否拥有身份凭证，所以是可以放行的
    // 没有jwt相当于匿名访问，若有一些接口是需要权限的，则不能访问这些接口
    if (StringUtils.isBlank(jwt)) {
      chain.doFilter(request, response);
      return;
    }

    // 拿到jwt-token中的claim判断是否过期
    Claims claim = jwtUtils.getClaimsByToken(jwt);
    if (claim == null) {
      throw new JwtException("token 异常");
    }
    if (jwtUtils.isTokenExpired(claim)) {
      throw new JwtException("token 已过期");
    }

    //  获取用户名和权限等信息封装UsernamePasswordAuthenticationToken
    String username = claim.getSubject();
    List<String> authorityStrings = (List<String>) claim.get("authorities");
    List<SimpleGrantedAuthority> authorities = authorityStrings.stream()
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());

    // 构建UsernamePasswordAuthenticationToken,这里密码为null，是因为提供了正确的JWT,实现自动登录
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
    SecurityContextHolder.getContext().setAuthentication(token);
    log.debug("jwt认证登录成功，username：{}", username);

    chain.doFilter(request, response);

  }
}

