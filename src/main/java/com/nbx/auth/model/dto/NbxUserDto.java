package com.nbx.auth.model.dto;

import com.nbx.auth.model.po.NbxUser;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author biscuitt
 * @version 1.0
 * @description User类扩展，记录了权限信息
 * @date 2023/6/15 11:25
 */

@Data
public class NbxUserDto extends NbxUser {

  List<String> permissions = new ArrayList<>();

}
