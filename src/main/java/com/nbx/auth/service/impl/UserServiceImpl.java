package com.nbx.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.nbx.auth.mapper.NbxRolePermissionsMapper;
import com.nbx.auth.model.dto.AuthParamsDto;
import com.nbx.auth.model.dto.NbxUserDto;
import com.nbx.auth.model.po.NbxPermissions;
import com.nbx.auth.model.po.NbxUser;
import com.nbx.auth.service.AuthService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author biscuitt
 * @version 1.0
 * @description 实现 UserDetailsService接口，自定义认证逻辑
 * @date 2023/6/15 11:20
 */

@Component
@Slf4j
public class UserServiceImpl implements UserDetailsService {

  @Resource
  NbxRolePermissionsMapper userPermissionsMapper;
  @Resource
  ApplicationContext applicationContext;

  @Resource
  PasswordEncoder passwordEncoder;


  /**
  * @description 重写 loadUserByUsername，使其支持多类型登录
  * @param s
  * @return org.springframework.security.core.userdetails.UserDetails
  * @author biscuitt
  * @date 2023/6/15 11:33
  */
  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

    // 拿到传入的authParamsDto来验证
    AuthParamsDto authParamsDto = null;
    try {
      authParamsDto = JSON.parseObject(s, AuthParamsDto.class);
    } catch (Exception e) {
      log.info("认证请求不符合项目要求:{}",s);
      throw new RuntimeException("认证请求数据格式不对");
    }

    // 策略模式，利用不同的拼接入参拿到的策略类执行不同策略 这里从applicationContext里拿到对应authType的authService
    String authType = authParamsDto.getAuthType();
    String beanName = authType + "AuthService";
    AuthService authService = applicationContext.getBean(beanName, AuthService.class);
    NbxUser user = authService.execute(authParamsDto);  // 在自定义的execute方法里去做验证

    // 验证之后封装userDetails
    UserDetails userDetails = getUserPrincipal(user);
    return userDetails;
  }

  /**
  * @description 获取 UserDetails，把传入的 userDto封装成 UserDetails
  * @param userDto
  * @return org.springframework.security.core.userdetails.UserDetails
  * @author biscuitt
  * @date 2023/6/15 14:45
  */
  private UserDetails getUserPrincipal(NbxUser userDto) {

    // 从数据库中查询用户权限 存入permissions数组中
    List<NbxPermissions> userPermissions = userPermissionsMapper.selectRolePermissionsByUserId(userDto.getId());
    List<String> permissions = new ArrayList<>();
    if (userPermissions.size() <= 0) {
      //用户权限,如果不加则报Cannot pass a null GrantedAuthority collection
      permissions.add("null");
    } else {
      permissions = userPermissions.stream().map(NbxPermissions::getPermissionCode).collect(Collectors.toList());
    }

    // 设置权限和密码
    String[] authorities = permissions.toArray(new String[0]);
    String password = passwordEncoder.encode(userDto.getPassword());
    String username = userDto.getUsername();

    UserDetails userDetails = User.withUsername(username).password(password).authorities(authorities).build();

    return userDetails;
  }
}
