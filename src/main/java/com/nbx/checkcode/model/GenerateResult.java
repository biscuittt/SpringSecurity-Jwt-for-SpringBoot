package com.nbx.checkcode.model;

import lombok.Data;

/**
 * @author biscuitt
 * @version 1.0
 * @description 验证码生成返回类
 * @date 2023/6/15 15:12
 */

@Data
public class GenerateResult {
  private String key;
  private String code;
}
