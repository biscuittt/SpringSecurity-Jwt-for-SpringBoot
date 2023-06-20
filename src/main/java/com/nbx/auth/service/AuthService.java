package com.nbx.auth.service;

import com.nbx.auth.model.dto.AuthParamsDto;
import com.nbx.auth.model.dto.NbxUserDto;
import com.nbx.auth.model.po.NbxUser;

/**
 * @author biscuitt
 * @version 1.0
 * @description 认证方式接口 不同认证方式有不同实现
 * @date 2023/6/15 11:24
 */

public interface AuthService {
  NbxUser execute(AuthParamsDto authParamsDto);
}