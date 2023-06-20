package com.nbx.checkcode.service;

import com.nbx.checkcode.model.CheckCodeParamsDto;
import com.nbx.checkcode.model.CheckCodeResultDto;
import com.nbx.checkcode.model.GenerateResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author biscuitt
 * @version 1.0
 * @description 验证码生成验证抽象类: 验证码生成和验证功能具体实现类
 * @date 2023/6/15 15:04
 */
@Slf4j
public abstract class AbstractCheckCodeService implements CheckCodeService {

    protected CheckCodeGenerator checkCodeGenerator;
    protected KeyGenerator keyGenerator;
    protected CheckCodeStore checkCodeStore;

    public abstract void  setCheckCodeGenerator(CheckCodeGenerator checkCodeGenerator);
    public abstract void  setKeyGenerator(KeyGenerator keyGenerator);
    public abstract void  setCheckCodeStore(CheckCodeStore CheckCodeStore);


    /**
     * @description 生成验证公用方法
     * @param checkCodeParamsDto 生成验证码参数
     * @param code_length 验证码长度
     * @param keyPrefix key的前缀
     * @param expire 过期时间
     * @return com.xuecheng.checkcode.service.AbstractCheckCodeService.GenerateResult 生成结果
    */
    public GenerateResult generate(CheckCodeParamsDto checkCodeParamsDto,
                                    Integer code_length, String keyPrefix, Integer expire){
        //生成四位验证码checkCode和一个checkCodeKey
        String code = checkCodeGenerator.generate(code_length);
        String key = keyGenerator.generate(keyPrefix);
        log.debug("生成验证码:{} 和checkCodeKey：{}", code, key);

        //存储验证码
        checkCodeStore.set(key,code,expire);

        //返回验证码生成结果
        GenerateResult generateResult = new GenerateResult();
        generateResult.setKey(key);
        generateResult.setCode(code);

        return generateResult;
    }


    public abstract CheckCodeResultDto generate(CheckCodeParamsDto checkCodeParamsDto);


    /**
    * @description 简易验证生成的验证码和 key
    * @param key
     * @param code
    * @return boolean
    */
    public boolean verify(String key, String code){
        if (StringUtils.isBlank(key) || StringUtils.isBlank(code)){
            return false;
        }
        String code_l = checkCodeStore.get(key);
        if (code_l == null){
            return false;
        }
        boolean result = code_l.equalsIgnoreCase(code);
        if(result){
            //删除验证码
            checkCodeStore.remove(key);
        }
        return result;
    }


}
