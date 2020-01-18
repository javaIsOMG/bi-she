package com.dayup.seckil.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Classname Student
 * @Description TODO
 * @Date 2020/1/2 14:41
 * @Created by Yinghao.He
 */
@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = 2984538664184265079L;
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String id;
    @Column(name = "student_name",nullable = false)
    private String studentName;
    @Column(name = "student_number",nullable = false,unique = true)
    private String studentNumber;
    @Column(name = "student_professional")
    private String studentProfessional;
    @Column(name = "sex",nullable = false)
    private Integer sex;
    @Column(name = "declaration")
    private String declaration;
    @Column(name = "phone")
    private String phone;
    @Column(name = "birth_time",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthTime;
    @Column(name = "age")
    private Integer age;
    @Column(name = "hobby")
    private String hobby;
    @Column(name= "pic_url")
    private String picUrl;
    @Column(name = "user_name",unique = true)
    private String userName;
}
