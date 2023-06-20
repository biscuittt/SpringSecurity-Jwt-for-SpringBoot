package com.nbx.auth.model.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * @author biscuitt
 * @version 1.0
 * @description TODO
 * @date 2023/6/15 11:29
 */

@Data
public class AuthParamsDto {

  private String username;  // 用户名

  private String password;  // 域  用于扩展

  private String cellphone;  // 手机号

  private String checkCode;  // 验证码

  private String checkCodeKey;  // 验证码key

  private String authType;  // 认证的类型   password:用户名密码模式类型 sms:短信模式类型 wx:微信登录

  //附加数据，作为扩展，不同认证类型可拥有不同的附加数据。如认证类型为短信时包含smsKey : sms:3d21042d054548b08477142bbca95cfa; 所有情况下都包含clientId
  private Map<String, Object> payload = new HashMap<>();
}
