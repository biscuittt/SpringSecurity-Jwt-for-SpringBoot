package com.nbx.checkcode.service;

import com.nbx.checkcode.model.CheckCodeParamsDto;
import com.nbx.checkcode.model.CheckCodeResultDto;

/**
 * @author biscuitt
 * @version 1.0
 * @description 验证码生成及验证 service
 * @date 2023/6/15 15:00
 */
public interface CheckCodeService {

  /**
   * @description 生成验证码
   * @param checkCodeParamsDto 生成验证码参数
   */
  CheckCodeResultDto generate(CheckCodeParamsDto checkCodeParamsDto);

  /**
   * @description 校验验证码
   * @param key checkCodeKey
   * @param code checkCode
   */
  public boolean verify(String key, String code);

}