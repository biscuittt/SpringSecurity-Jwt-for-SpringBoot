package com.nbx.checkcode.model;

import lombok.Data;

/**
* @description 验证码入参扩展类
* @version 1.0
* @author biscuitt
* @date 2023/6/15 14:58
*/
@Data
public class CheckCodeParamsDto {

    /**
     * 验证码类型:pic、sms、email等
     */
    private String checkCodeType;

    /**
     * 业务携带参数
     */
    private String param1;
    private String param2;
    private String param3;
}
