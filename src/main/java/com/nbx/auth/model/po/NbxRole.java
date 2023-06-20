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
 * @description 数据库中 角色类
 * @date 2023/6/19 10:23
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("nbx_role")
public class NbxRole implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String roleName;

  private String role;

  private String status;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;
}
