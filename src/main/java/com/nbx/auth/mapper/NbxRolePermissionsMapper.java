package com.nbx.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nbx.auth.model.po.NbxPermissions;
import com.nbx.auth.model.po.NbxRolePermissions;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author biscuitt
 * @version 1.0
 * @description nbxPermissionMapper mapper映射接口
 * @date 2023/6/15 11:43
 */

@Mapper
public interface NbxRolePermissionsMapper extends BaseMapper<NbxRolePermissions> {

  // todo 实现用 userId查 user—role表，查出角色后，再去查角色对应权限
  @Select("select * from nbxUserPermissions")
  List<NbxPermissions> selectRolePermissionsByUserId(@Param("userId") Long userId);
}
