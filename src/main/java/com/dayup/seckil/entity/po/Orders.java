package com.dayup.seckil.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @Classname Orders
 * @Description TODO
 * @Date 2019/12/24 15:54
 * @Created by Yinghao.He
 */
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {
    private static final long serialVersionUID = 3530684552997184852L;
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private BigInteger orderId;
    @Column(name = "course_no",nullable = false)
    private String courseNo;
    @Column(name = "username",nullable = false)
    private String userName;
    @Column(name = "course_name",nullable = false)
    private String courseName;
    @Column(name = "course_price")
    private BigDecimal coursePrice;
    @Column(name = "pay_price")
    private BigDecimal payPrice;
    @Column(name = "payment")
    private String payMent;
    @Column(name = "pay_status")
    private String payStatus;
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreateDate;
    @Column(name = "creat_by")
    private String creatBy;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "course_pic")
    private String coursePic;
}
