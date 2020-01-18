package com.dayup.seckil.service.serviceImpl;


import com.dayup.seckil.redis.CourseRedis;
import com.dayup.seckil.util.IPUtil;
import com.dayup.seckil.util.UUIDUtil;
import com.dayup.seckil.entity.po.Course;
import com.dayup.seckil.entity.po.Orders;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.CourseVo;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.entity.vo.ResultCode;
import com.dayup.seckil.redis.SeckillRedis;
import com.dayup.seckil.repository.SeckillRepository;
import com.dayup.seckil.service.CourseService;
import com.dayup.seckil.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.*;

/**
 * @Classname SeckillServiceImpl
 * @Description TODO
 * @Date 2019/12/24 16:20
 * @Created by Yinghao.He
 */
@Slf4j
@Service
@Transactional
public class SeckillServiceImpl implements SeckillService {
    @Autowired
    private SeckillRepository seckillRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRedis courseRedis;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    @Autowired
    private SeckillRedis seckillRedis;
    private static Map<String,Boolean> map=new HashMap<>();
    @Override
    public Result<Orders> seckillFlow(User user, String courseNo, String path, HttpServletRequest request) {
        String ipAddr = IPUtil.getIpAddr(request);
        if (seckillRedis.dec(ipAddr,1,30)>=10){
            return Result.failure(ResultCode.REQUEST_TOO_MUCH);
        }
        Orders orders=seckillRepository.findByUserNameAndCourseNo(user.getUsername(),courseNo);
        if (orders!=null){
            log.info("已经下单");
            return Result.failure(ResultCode.COURSE_HAVE_APPOINTMENT);
        }
        String string = seckillRedis.getString(path);
        if (string==null){
            return Result.failure(ResultCode.NOT_WAIT_LINK);
        }else if (!string.equals(path)){
            return Result.failure(ResultCode.NOT_WAIT_LINK);
        }
        //判断库存
        Boolean isPass=map.get(courseNo);
//        if (isPass){
//            return Result.failure(ResultCode.COURSE_OUT_STOCK);
//        }
        //判断是否已经下单
        double stockQuantity = courseRedis.dec(courseNo, -1);
        if (stockQuantity<0){
            log.info("无库存");
            map.put(courseNo,true);
            return Result.failure(ResultCode.COURSE_OUT_STOCK);
        }
        //库存减(传发给consumer)
        kafkaTemplate.send("test",courseNo+"hello"+ user.getUsername());
        return Result.failure(ResultCode.WAIT_LINK);
    }
    @Override
    public void cacheAllCourse() {
        List<Course> courseList = courseService.getCourseList();
        if (courseList==null){
            return;
        }
        for (Course course:courseList){
            courseRedis.setString(course.getCourseNo(),course.getStockQuantity(),-1);
            courseRedis.put(course.getCourseNo(),course,-1);
            map.put(course.getCourseNo(),false);
        }
    }
    public Orders InventoryReduction(User user,String courseNo) {
        CourseVo courseVo=new CourseVo();
        Course o =(Course) courseRedis.get(courseNo);
        courseVo.setCourse(o);
        //减库存
        //courseRedis.dec(courseNo,-1);
        int success =courseService.reduceStock(courseVo.getCourse().getCourseNo());
        if (success>0){
            log.info("减库存成功");
            Orders orders=new Orders();
            orders.setCourseNo(courseVo.getCourse().getCourseNo());
            orders.setCourseName(courseVo.getCourse().getCourseName());
            orders.setCoursePic(courseVo.getCourse().getCoursePic());
            orders.setCoursePrice(courseVo.getCourse().getCoursePrice());
            orders.setPayPrice(courseVo.getCourse().getCoursePrice());
            orders.setCreatBy(user.getUsername());
            orders.setCreateDate(new Date(System.currentTimeMillis()));
            orders.setUserName(user.getUsername());
            orders.setPayStatus("0");
            //生成订单
            Orders result = seckillRepository.saveAndFlush(orders);
            log.info("生成新订单");
            return result;
        }
        log.info("减库存失败");
        return null;
    }

    @Override
    public String getPath(User user, String courseNo) {
        String path = UUIDUtil.UUID();
        seckillRedis.setString(path,path,-1);
        return path;
    }

    @Override
    public Result<Orders> seckillResult(User user, String courseNo) {
        Orders orders = seckillRepository.findByUserNameAndCourseNo(user.getUsername(), courseNo);
        if(orders == null){
            log.info("没有订单");
            return Result.failure(ResultCode.NOT_STOCK);
        }
        log.info("获取订单详情");
        return Result.success(orders);
    }

    @Override
    public Result<List<Orders>> getListOrders(User user) {
        List<Orders> all = seckillRepository.findAllByUserName(user.getUsername());
        return Result.success(all);
    }

    @Override
    public Result getOrdersByOrderIdAndUserName(BigInteger orderId) {
        Optional<Orders> orders = seckillRepository.findByOrderId(orderId);
        Orders result = orders.get();
        if (result==null){
            return Result.failure();
        }
        return Result.success(result);
    }
}
