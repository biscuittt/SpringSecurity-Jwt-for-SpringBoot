package com.nbx.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author biscuitt
 * @version 1.0
 * @description TODO
 * @date 2023/6/15 16:05
 */

@Data
@NoArgsConstructor
public class ResponseResult<T> {

  private Integer code;

  private String msg;

  private T data;

  public ResponseResult(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  /**
  * @description 成功返回封装
  * @return com.nbx.common.model.ResponseResult<T>
  * @author biscuitt
  * @date 2023/6/15 16:13
  */
  public static <T> ResponseResult<T> success() {
    ResponseResult<T> response = new ResponseResult<T>();
    response.setCode(200);
    return response;
  }
  public static <T> ResponseResult<T> success(String msg) {
    ResponseResult<T> response = new ResponseResult<T>();
    response.setCode(200);
    response.setMsg(msg);
    return response;
  }
  public static <T> ResponseResult<T> success(int code, T data) {
    ResponseResult<T> response = new ResponseResult<T>();
    response.setCode(200);
    response.setData(data);
    return response;
  }

  /**
  * @description 错误信息封装
  * @return com.nbx.common.model.ResponseResult<T>
  * @author biscuitt
  * @date 2023/6/15 16:14
  */
  public static <T> ResponseResult<T> validFail(String msg) {
    ResponseResult<T> response = new ResponseResult<T>();
    response.setCode(-1);
    response.setMsg(msg);
    return response;
  }
  public static <T> ResponseResult<T> validFail(T data,String msg) {
    ResponseResult<T> response = new ResponseResult<T>();
    response.setCode(-1);
    response.setData(data);
    response.setMsg(msg);
    return response;
  }


}
