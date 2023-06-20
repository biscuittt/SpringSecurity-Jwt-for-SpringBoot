package com.nbx.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nbx.auth.model.po.NbxUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author biscuitt
 * @version 1.0
 * @description NbxUserMapper mapper映射接口
 * @date 2023/6/15 15:51
 */

@Mapper
public interface NbxUserMapper extends BaseMapper<NbxUser> {

  @Select("select * from nbx_user where username = #{username}")
  NbxUser getUserByUsername(String username);

}