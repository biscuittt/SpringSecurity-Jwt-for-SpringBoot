package com.nbx.checkcode.service;

/**
 * @author biscuitt
 * @version 1.0
 * @description checkcodekey生成器
 * @date 2023/6/15 15:02
 */
public interface KeyGenerator {

  /**
   * key生成
   * @return 验证码
   */
  String generate(String prefix);
}