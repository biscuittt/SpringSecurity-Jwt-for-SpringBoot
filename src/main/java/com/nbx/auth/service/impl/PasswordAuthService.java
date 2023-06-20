package com.nbx.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nbx.auth.mapper.NbxUserMapper;
import com.nbx.auth.model.dto.AuthParamsDto;
import com.nbx.auth.model.dto.NbxUserDto;
import com.nbx.auth.model.po.NbxUser;
import com.nbx.auth.service.AuthService;
import com.nbx.base.exception.CaptchaException;
import com.nbx.base.exception.CommonError;
import com.nbx.checkcode.service.CheckCodeService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author biscuitt
 * @version 1.0
 * @description 用账号密码方式登录执行的策略
 * @date 2023/6/15 14:47
 */

@Service
@Slf4j
public class PasswordAuthService implements AuthService {

  @Resource
  PasswordEncoder passwordEncoder;

  @Resource
  CheckCodeService checkCodeService;

  @Resource
  NbxUserMapper nbxUserMapper;

  @Override
  public NbxUser execute(AuthParamsDto authParamsDto) {

    // 拿到checkCode或checkCodeKey
    String checkCode = authParamsDto.getCheckCode();
    String checkCodeKey = authParamsDto.getCheckCodeKey();

    // 验证码校验
    if (StringUtils.isBlank(checkCode) || StringUtils.isBlank(checkCodeKey)) {
      log.debug("checkCode或checkCodeKey为空！");
      throw new CaptchaException(CommonError.Captcha_NULL.getErrMsg());
    }
    boolean verify = checkCodeService.verify(checkCodeKey, checkCode);
    if (!verify) {
      throw new CaptchaException(CommonError.Captcha_ERROR.getErrMsg());
    }

    // 校验用户名是否存在
    String username = authParamsDto.getUsername();
    NbxUser nbxUser = nbxUserMapper.selectOne(
        new LambdaQueryWrapper<NbxUser>().eq(NbxUser::getUsername, username));
    if (nbxUser == null) throw new RuntimeException("账号或密码错误！");

    // 校验密码是否正确
    String passwordDb = nbxUser.getPassword();
    String passwordFrom = authParamsDto.getPassword();
    boolean matches = passwordEncoder.matches(passwordFrom, passwordDb);
    if (!matches) throw new RuntimeException("账号或密码错误！");
    log.info("用户：{} 登陆成功！", nbxUser.toString());

    return nbxUser;
  }
}
