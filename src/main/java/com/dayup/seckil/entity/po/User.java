package com.dayup.seckil.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Classname User
 * @Description TODO
 * @Date 2019/12/18 16:49
 * @Created by Yinghao.He
 */
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "username",nullable = false)
    @NotBlank(message = "用户名不能为空")
    private String username;
    @Column(name = "password",nullable = false)
    @NotBlank(message = "密码不能为空")
    private String password;
    @Column(name = "id",nullable = false)
    private Integer id;
    private String repassword;
    @Column(name = "salt",nullable = false)
    private String salt;
}
