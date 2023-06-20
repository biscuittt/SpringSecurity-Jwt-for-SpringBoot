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
 * @description 数据库中 permission字典类
 * @date 2023/6/19 10:14
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("nbx_permissions")
public class NbxPermissions implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String permissionName;

  private String permissionCode;

  private String isMenu;

  private Integer level;

  private Integer sort;

  private Long parentId;

  private String status;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;
}
