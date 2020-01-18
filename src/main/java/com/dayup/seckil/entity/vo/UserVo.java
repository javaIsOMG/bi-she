package com.dayup.seckil.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname UserVo
 * @Description TODO
 * @Date 2019/12/21 11:34
 * @Created by Yinghao.He
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {
    private static final long serialVersionUID = -6456429251061545908L;
    private String username;
    private String password;
    private Integer id;
    private String repassword;
    private String salt;

}
