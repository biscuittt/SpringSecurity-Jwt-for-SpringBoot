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
 * @description 数据库中认证 User类
 * @date 2023/6/15 11:26
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("nbx_user")
public class NbxUser implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;

  private String password;

  private String wxUnionid;

  private String nickname;

  private String userpic;

  private String phone;

  private String email;

  private String sex;

  private String status;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;
}
