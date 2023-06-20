package com.nbx.checkcode.service;

/**
 * @author biscuitt
 * @version 1.0
 * @description 验证码生成器
 * @date 2023/6/15 15:02
 */
public interface CheckCodeGenerator {

  /**
   * 验证码生成
   * @return 验证码
   */
  String generate(int length);

}