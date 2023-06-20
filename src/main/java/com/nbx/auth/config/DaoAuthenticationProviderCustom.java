package com.nbx.auth.config;

import com.nbx.auth.service.impl.UserServiceImpl;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author biscuitt
 * @version 1.0
 * @description 自定义 DaoAuthenticationProvider
 * @date 2023/6/15 11:09
 */

@Component
public class DaoAuthenticationProviderCustom extends DaoAuthenticationProvider{

  @Resource
  UserServiceImpl userDetailsService;

  public void setUserDetailsService(UserDetailsService userDetailsService) {
    this.userDetailsService = (UserServiceImpl) userDetailsService;
  }

  //屏蔽密码对比
  protected void additionalAuthenticationChecks(UserDetails userDetails,
      UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {
  }
}
