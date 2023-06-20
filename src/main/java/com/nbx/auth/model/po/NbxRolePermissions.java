package com.nbx.auth.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author biscuitt
 * @version 1.0
 * @description 数据库中用户拥有权限类 Permission
 * @date 2023/6/15 11:39
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("nbx_role_permissions")
public class NbxRolePermissions implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private Long roleId;

  private Long permissionId;

  private LocalDateTime createTime;
}
