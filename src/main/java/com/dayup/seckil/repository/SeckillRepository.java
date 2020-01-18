package com.dayup.seckil.repository;

import com.dayup.seckil.entity.po.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * @Classname SeckillRepository
 * @Description TODO
 * @Date 2019/12/24 16:20
 * @Created by Yinghao.He
 */
@Repository
public interface SeckillRepository extends JpaRepository<Orders,String> {
    Orders findByUserNameAndCourseNo(String username, String courseNo);

    Optional<Orders> findByOrderId(BigInteger orderId);

    List<Orders> findAllByUserName(String userName);
}
