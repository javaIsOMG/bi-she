package com.dayup.seckil.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * @Classname Mp4
 * @Description TODO
 * @Date 2020/1/9 11:17
 * @Created by Yinghao.He
 */
@Entity
@Table(name = "mp4")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mp4 {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private BigInteger id;
    @Column(name = "name")
    private String mp4Name;
    @Column(name="blues",nullable = false)
    private Integer blues;
    @Column(name = "course_no",nullable = false)
    private String courseNo;
    @Column(name = "url",unique = true,nullable = false)
    private String url;
}
