package com.nbx.base.config;

/**
 * @author biscuitt
 * @version 1.0
 * @description TODO
 * @date 2023/6/19 10:52
 */

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 全局序列化配置类
 */
@Configuration
@EnableWebMvc
public class MyFastJsonConfig extends WebMvcConfigurationSupport {
  /**
   * 配置fastjson方式一：(map集合)保留非实体构造方法的null，构造方法中的null格式化为——""
   * 配置fastjson方式二：在SpringBoot启动类中(@SpringBootApplication)，注入Bean: HttpMessageConverters
   * (list集合+单个对象实例) 中的null，均能够格式为——""
   * @param converters
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //创建会话消息实例容器
    FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
    //创建fastJson配置实例
    FastJsonConfig config = new FastJsonConfig();
    config.setSerializerFeatures(
        //全局修改日期格式,如果时间是data、时间戳类型，按照这种格式初始化时间 "yyyy-MM-dd HH:mm:ss"
        SerializerFeature.WriteDateUseDateFormat,
        // 保留 Map 空的字段
        SerializerFeature.WriteMapNullValue,
        // 将 String 类型的 null 转成""
        SerializerFeature.WriteNullStringAsEmpty,
        // 将 Number 类型的 null 转成 0
        SerializerFeature.WriteNullNumberAsZero,
        // 将 List 类型的 null 转成 []
        SerializerFeature.WriteNullListAsEmpty,
        // 将 Boolean 类型的 null 转成 false
        SerializerFeature.WriteNullBooleanAsFalse,
        // 避免循环引用
        SerializerFeature.DisableCircularReferenceDetect,
        //返回Json数据排版格式
        SerializerFeature.PrettyFormat);
    //按字段名称排序后输出-SerializerFeature.SortField
    //设置配置实例
    converter.setFastJsonConfig(config);
    //设置默认编码方式
    converter.setDefaultCharset(StandardCharsets.UTF_8);
    //集合填入媒介类型
    List<MediaType> mediaTypeList = new ArrayList<>();
    // 解决中文乱码问题，相当于在 Controller 上的 @RequestMapping 中加了个属性 produces = "application/json"
    mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
    //设置支持媒介——装载了解决中文乱码参数
    converter.setSupportedMediaTypes(mediaTypeList);
    //添加到会话中
    converters.add(converter);
  }
}
