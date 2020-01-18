package com.dayup.seckil.entity.vo;

import com.dayup.seckil.entity.po.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname CourseVo
 * @Description TODO
 * @Date 2019/12/24 13:52
 * @Created by Yinghao.He
 */
@Data
public class CourseVo implements Serializable {
    private static final long serialVersionUID = -6239842691641149094L;
    private Course course;
    private int courseStatus=0;
    private int remainTime=0;
}
