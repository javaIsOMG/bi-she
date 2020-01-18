package com.dayup.seckil.service;

import com.dayup.seckil.entity.po.Orders;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.CourseVo;
import com.dayup.seckil.entity.vo.Result;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

/**
 * @Classname SeckillService
 * @Description TODO
 * @Date 2019/12/24 16:19
 * @Created by Yinghao.He
 */
public interface SeckillService {
    Result<Orders> seckillFlow(User user, String courseNo, String path, HttpServletRequest request);

    void cacheAllCourse();
    Orders InventoryReduction(User user, String courseNo);

    String getPath(User user, String courseNo);

    Result<Orders> seckillResult(User user, String courseNo);

    Result<List<Orders>> getListOrders(User user);

    Result getOrdersByOrderIdAndUserName(BigInteger courseNo);
}
