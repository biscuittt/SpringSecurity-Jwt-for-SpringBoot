package com.nbx.auth.service;

import com.nbx.auth.model.po.NbxUser;
import org.apache.ibatis.annotations.Select;

/**
 * @author biscuitt
 * @version 1.0
 * @description NbxUserService User实体类 Service
 * @date 2023/6/20 15:26
 */
public interface NbxUserService {

  NbxUser getUserByUsername(String username);

}