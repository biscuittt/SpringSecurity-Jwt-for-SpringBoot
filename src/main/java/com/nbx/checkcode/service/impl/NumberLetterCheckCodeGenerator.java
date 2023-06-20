package com.nbx.checkcode.service.impl;

import com.nbx.checkcode.service.CheckCodeGenerator;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author biscuitt
 * @version 1.0
 * @description 数字字母类型验证码生成器
 * @date 2023/6/15 15:14
 */

@Slf4j
@Service
public class NumberLetterCheckCodeGenerator implements CheckCodeGenerator {

  @Override
  public String generate(int length) {

    String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random random = new Random();
    StringBuffer sb = new StringBuffer();

    // 36个字母数字中生成随机数选择对应下标的字符，拼成一个length长的验证码
    for(int i = 0; i < length; i++){
      int number=random.nextInt(36);
      sb.append(str.charAt(number));
    }
    return sb.toString();
  }

}
