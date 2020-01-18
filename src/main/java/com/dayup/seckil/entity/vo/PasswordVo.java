package com.dayup.seckil.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname PasswordVo
 * @Description TODO
 * @Date 2020/1/4 11:45
 * @Created by Yinghao.He
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordVo {
    private String password;
    private String newpassword;
}
