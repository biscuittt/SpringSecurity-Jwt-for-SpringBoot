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
 * @description 数据库中用户对应角色类
 * @date 2023/6/19 9:26
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("nbx_user_role")
public class NbxUserRole implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private Long userId;

  private Long roleId;

  private LocalDateTime createTime;
}
