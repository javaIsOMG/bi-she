package com.dayup.seckil.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Classname Course
 * @Description TODO
 * @Date 2019/12/24 9:23
 * @Created by Yinghao.He
 */
@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course implements Serializable {
    private static final long serialVersionUID = -8103056468144287005L;
    @Id
    @Column(name = "course_no")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String courseNo;
    @Column(name = "course_name",nullable = false)
    private String courseName;
    @Column(name = "teacher_name",nullable = false)
    private String teacherName;
    @Column(name = "course_desciption")
    private String courseDescription;
    @Column(name = "course_period")
    private String coursePeriod;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Column(name = "course_price")
    private BigDecimal coursePrice;
    @Column(name = "stock_quantity")
    private Integer stockQuantity;
    @Column(name = "course_type")
    private Integer courseType;
    @Column(name = "course_pic")
    private String coursePic;

}
