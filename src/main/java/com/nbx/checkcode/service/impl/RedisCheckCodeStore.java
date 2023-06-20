package com.nbx.checkcode.service.impl;

import com.nbx.checkcode.service.CheckCodeStore;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author biscuitt
 * @version 1.0
 * @description Redis 存储生成的验证码 封装
 * @date 2023/6/15 15:18
 */
public class RedisCheckCodeStore implements CheckCodeStore {

  @Resource
  RedisTemplate redisTemplate;

  @Override
  public void set(String key, String value, Integer expire) {
    redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
  }

  @Override
  public String get(String key) {
    return (String)redisTemplate.opsForValue().get(key);
  }

  @Override
  public void remove(String key) {
    redisTemplate.delete(key);
  }

}
