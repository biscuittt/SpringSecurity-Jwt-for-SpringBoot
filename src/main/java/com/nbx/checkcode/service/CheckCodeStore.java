package com.nbx.checkcode.service;

/**
 * @author biscuitt
 * @version 1.0
 * @description Redis 或 本地缓存 存储验证码
 * @date 2023/6/15 15:03
 */
public interface CheckCodeStore {

  /**
   * @description 向缓存设置key
   * @param key key
   * @param value value
   * @param expire 过期时间,单位秒
   */
  void set(String key, String value, Integer expire);

  String get(String key);

  void remove(String key);
}