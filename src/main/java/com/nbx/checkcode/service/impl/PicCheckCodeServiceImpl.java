package com.nbx.checkcode.service.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.nbx.checkcode.model.CheckCodeParamsDto;
import com.nbx.checkcode.model.CheckCodeResultDto;
import com.nbx.checkcode.model.GenerateResult;
import com.nbx.checkcode.service.AbstractCheckCodeService;
import com.nbx.checkcode.service.CheckCodeGenerator;
import com.nbx.checkcode.service.CheckCodeService;
import com.nbx.checkcode.service.CheckCodeStore;
import com.nbx.checkcode.service.KeyGenerator;
import com.nbx.common.utils.EncryptUtil;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

/**
 * @author biscuitt
 * @version 1.0
 * @description 验证码图片生成器
 * @date 2023/6/15 15:04
 */
@Slf4j
@Service
public class PicCheckCodeServiceImpl extends AbstractCheckCodeService implements CheckCodeService {

  @Resource
  DefaultKaptcha kaptcha;

  @Resource
  ApplicationContext applicationContext;

  @Override
  public void setCheckCodeGenerator(CheckCodeGenerator checkCodeGenerator) {
    this.checkCodeGenerator = (CheckCodeGenerator) applicationContext.getBean("NumberLetterCheckCodeGenerator");
  }

  @Override
  public void setKeyGenerator(KeyGenerator keyGenerator) {
    this.keyGenerator = (KeyGenerator) applicationContext.getBean("UUIDKeyGenerator");
  }

  @Override
  public void setCheckCodeStore(CheckCodeStore checkCodeStore) {
    CheckCodeStore redisCheckCodeStore = (com.nbx.checkcode.service.CheckCodeStore) applicationContext.getBean("RedisCheckCodeStore");
    if (!checkCodeStore.getClass().isAssignableFrom(RedisCheckCodeStore.class)) {
      this.checkCodeStore = checkCodeStore;
    }
    this.checkCodeStore = redisCheckCodeStore;
  }

  /**
  * @description 生成图片验证码并返回 base64
  * @param checkCodeParamsDto
  * @return com.nbx.checkcode.model.CheckCodeResultDto
  * @author biscuitt
  * @date 2023/6/15 15:34
  */
  @Override
  public CheckCodeResultDto generate(CheckCodeParamsDto checkCodeParamsDto) {

    // 生成图片验证码并返回base64
    GenerateResult generateResult = generate(checkCodeParamsDto, 4, "checkcode:", 120);
    String key = generateResult.getKey();
    String code = generateResult.getCode();
    String pic = createPic(code);

    // 存入CheckCodeResultDto后并返回
    CheckCodeResultDto checkCodeResultDto = new CheckCodeResultDto();
    checkCodeResultDto.setAliasing(pic);
    checkCodeResultDto.setKey(key);
    return checkCodeResultDto;
  }

  /**
  * @description 生成验证码图片，调用 EncryptUtil
  * @param code
  * @return java.lang.String
  * @author biscuitt
  * @date 2023/6/15 15:35
  */
  private String createPic(String code) {

    // 生成图片验证码
    ByteArrayOutputStream outputStream = null;
    BufferedImage image = kaptcha.createImage(code);

    outputStream = new ByteArrayOutputStream();
    String imgBase64Encoder = null;

    try {
      // 对字节数组Base64编码
      BASE64Encoder base64Encoder = new BASE64Encoder();
      ImageIO.write(image, "png", outputStream);
      imgBase64Encoder = "data:image/png;base64," + EncryptUtil.encodeBase64(outputStream.toByteArray());
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        outputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return imgBase64Encoder;
  }
}
