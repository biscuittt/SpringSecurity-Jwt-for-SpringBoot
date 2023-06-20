package com.nbx.checkcode.service.impl;

import com.nbx.checkcode.service.KeyGenerator;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author biscuitt
 * @version 1.0
 * @description uuid生成器
 * @date 2023/6/15 15:17
 */

@Slf4j
@Service
public class UUIDKeyGenerator implements KeyGenerator {

  @Override
  public String generate(String prefix) {
    String uuid = UUID.randomUUID().toString();
    return prefix + uuid.replaceAll("-", "");
  }

}
