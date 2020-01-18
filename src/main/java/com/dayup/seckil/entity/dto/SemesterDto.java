package com.dayup.seckil.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Classname SemesterDto
 * @Description TODO
 * @Date 2020/1/14 18:11
 * @Created by Yinghao.He
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDto<T> {
    //总学分数
    private Integer totalScore;
    //中文学期名
    private String semesterCN;
    //成绩集合
    private List<T> recordList;
}
