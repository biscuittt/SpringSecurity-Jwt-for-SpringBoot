package com.nbx.auth.config;

import com.nbx.auth.service.filter.JwtAuthenticationFilter;
import com.nbx.auth.service.handler.AccessDeniedHandlerImpl;
import com.nbx.auth.service.handler.JWTLogoutSuccessHandler;
import com.nbx.auth.service.handler.JwtAuthenticationEntryPoint;
import com.nbx.auth.service.handler.LoginFailureHandler;
import com.nbx.auth.service.handler.LoginSuccessHandler;
import com.nbx.auth.service.impl.UserServiceImpl;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author biscuitt
 * @version 1.0
 * @description spring security 配置类
 * @date 2023/6/15 10:55
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource
  DaoAuthenticationProviderCustom daoAuthenticationProviderCustom;

  @Resource
  LoginFailureHandler loginFailureHandler;

  @Resource
  LoginSuccessHandler loginSuccessHandler;

  @Resource
  JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Resource
  AccessDeniedHandlerImpl accessDeniedHandler;

  @Resource
  UserServiceImpl userDetailService;

  @Resource
  JwtAuthenticationFilter jwtAuthenticationFilter;

  @Resource
  JWTLogoutSuccessHandler jwtLogoutSuccessHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProviderCustom);
  }

  private static final String[] URL_WHITELIST = {
      "/login",
      "/logout",
      "/captcha",
      "/favicon.ico"
  };

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors()
        .and()
        .csrf().disable()  // 关闭 csrf

        .formLogin()
        .successHandler(loginSuccessHandler)
        .failureHandler(loginFailureHandler)

        .and()
        .logout()
        .logoutSuccessHandler(jwtLogoutSuccessHandler)

        .and()
        .authorizeRequests()   // 接收请求
        .anyRequest().permitAll()
//        .antMatchers(URL_WHITELIST).permitAll() // 放行路径，不需登录
//        .anyRequest().authenticated()  // 除放行路径外都要认证

        .and()
        .exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)  // 抛出异常后的处理逻辑
        .accessDeniedHandler(accessDeniedHandler)  // 认证失败逻辑

        .and()
        .addFilter(jwtAuthenticationFilter);
  }

  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();  //配置认证管理bean
  }
}
