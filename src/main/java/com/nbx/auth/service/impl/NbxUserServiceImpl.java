package com.nbx.auth.service.impl;

import com.nbx.auth.mapper.NbxUserMapper;
import com.nbx.auth.model.po.NbxUser;
import com.nbx.auth.service.NbxUserService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author biscuitt
 * @version 1.0
 * @description NbxUserService实现类
 * @date 2023/6/20 15:27
 */

@Service
@Slf4j
public class NbxUserServiceImpl implements NbxUserService {

  @Resource
  NbxUserMapper nbxUserMapper;


  @Override
  public NbxUser getUserByUsername(String username) {
    return nbxUserMapper.getUserByUsername(username);
  }
}
