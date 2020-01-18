package com.dayup.seckil.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Pageable;
import java.util.Date;

/**
 * @Classname StudentVo
 * @Description TODO
 * @Date 2020/1/7 11:05
 * @Created by Yinghao.He
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentVo {
    private String studentName;
    private String studentNumber;
    private String declaration;
    private Integer sex;
    private String phone;
    private Integer age;
    private String hobby;
    private String studentProfessional;
    private String time;
    private String userName;
    private String password;
}
