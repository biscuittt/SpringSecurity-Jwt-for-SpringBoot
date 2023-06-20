package com.nbx.base.exception;

/**
 * @author biscuitt
 * @version 1.0
 * @description 通用错误信息
 * @date 2023/6/19 16:02
 */
public enum CommonError {

    Captcha_ERROR("验证码输入错误，请重试。"),
    Captcha_NULL("未输入验证码或验证码输入为空，请重试。"),
    UNKOWN_ERROR("执行过程异常，请重试。"),
    PARAMS_ERROR("非法参数"),
    OBJECT_NULL("对象为空"),
    QUERY_NULL("查询结果为空"),
    REQUEST_NULL("请求参数为空");


    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    private CommonError( String errMsg) {
        this.errMsg = errMsg;
    }

}