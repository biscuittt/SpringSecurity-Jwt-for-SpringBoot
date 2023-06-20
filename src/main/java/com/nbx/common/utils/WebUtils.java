package com.nbx.common.utils;

import com.alibaba.fastjson.JSON;
import com.nbx.base.exception.CaptchaException;
import com.nbx.common.model.ResponseResult;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @author biscuitt
 * @version 1.0
 * @description TODO
 * @date 2023/6/15 16:17
 */
public class WebUtils
{
  /**
   * 将字符串渲染到客户端
   *
   * @param response 渲染对象
   * @param responseCode 返回的状态码
   * @param msg 返回的信息
   * @return null
   */
  public static void renderString(HttpServletResponse response, Integer responseCode, String msg)
      throws IOException {
//    try
//    {
//      response.setStatus(200);
//      response.setContentType("application/json");
//      response.setCharacterEncoding("utf-8");
//      response.getWriter().print(string);
//    }
//    catch (IOException e)
//    {
//      e.printStackTrace();
//    }
//    return null;

    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(responseCode);
    ServletOutputStream outputStream = response.getOutputStream();

    ResponseResult result = new ResponseResult<>(responseCode, msg);

    // 返回给前端
    outputStream.write(JSON.toJSONBytes(result));
    outputStream.flush();
    outputStream.close();
  }
}
