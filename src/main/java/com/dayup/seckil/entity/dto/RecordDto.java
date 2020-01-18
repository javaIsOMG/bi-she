package com.dayup.seckil.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @Classname RecordDto
 * @Description TODO
 * @Date 2020/1/14 18:15
 * @Created by Yinghao.He
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class RecordDto {
    private Integer semesterType;
    private Integer semesterYears;
    private String courseName;
    private Integer score;
    private Integer studyScore;
    private Integer actualStudyScore;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date testTime;
    private String creator;
    private Integer isBk;
    private Integer isCx;
}
