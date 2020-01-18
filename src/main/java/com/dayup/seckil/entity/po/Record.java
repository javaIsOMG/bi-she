package com.dayup.seckil.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Classname Record
 * @Description TODO
 * @Date 2020/1/14 17:47
 * @Created by Yinghao.He
 */
@Entity
@Table(name = "school_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record implements Serializable {
    private static final long serialVersionUID = 5852424740895128985L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "course_name",nullable = false)
    private String courseName;
    @Column(name = "student_num",nullable = false)
    private String studentNum;
    @Column(name = "student_name",nullable = false)
    private String studentName;
    @Column(name = "score",nullable = false)
    private Integer score;
    @Column(name = "study_score",nullable = false)
    private Integer studyScore;
    @Column(name = "actual_study_score",nullable = false)
    private Integer actualStudyScore;
    @Column(name = "test_time")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date testTime;
    @Column(name = "semester_years")
    private Integer semesterYears;
    @Column(name = "semester_type")
    private Integer semesterType;
    @Column(name = "creator")
    private String creator;
    @Column(name = "is_bk")
    private Integer isBk;
    @Column(name = "is_cx")
    private Integer isCx;
}
