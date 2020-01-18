package com.dayup.seckil.controller;

import com.dayup.seckil.commom.BaseController;
import com.dayup.seckil.entity.po.Orders;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.service.SeckillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Classname SeckillController
 * @Description TODO
 * @Date 2019/12/24 16:06
 * @Created by Yinghao.He
 */
@Api(tags = "下单")
@Slf4j
@RestController //InitializingBean 初始化
public class SeckillController extends BaseController implements InitializingBean {
    @Autowired
    private SeckillService seckillService;
    @ApiOperation(notes = "注册",value = "用户密码重复密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "账号", dataType = "String"),
            @ApiImplicitParam(name = "password",value = "密码",dataType = "String"),
            @ApiImplicitParam(name = "repassword",value = "重复密码",dataType = "String")
    })
    @RequestMapping(value = "{path}/seckill/{courseNo}",method = RequestMethod.GET)
    public Result<Orders> seckill(User user, @PathVariable(name = "courseNo") String courseNo,
                                  @PathVariable(name = "path") String path,
                                  HttpServletRequest request){
        Result<Orders> result=seckillService.seckillFlow(user,courseNo,path,request);
        return result;
    }
    @RequestMapping(value = "getPath/{courseNo}",method = RequestMethod.GET)
    public String getPath(User user,@PathVariable(name = "courseNo") String courseNo){
        return seckillService.getPath(user,courseNo);
    }
    @RequestMapping(value="seckillResult/{courseNo}",method=RequestMethod.GET)
    public Result<Orders> seckillResult(User user, @PathVariable String courseNo){
        if(user == null){
            return Result.failure();
        }
        return seckillService.seckillResult(user, courseNo);
    }
    @RequestMapping(value="/orderList",method=RequestMethod.GET)
    public Result<List<Orders>> getOrders(User user){
        return seckillService.getListOrders(user);
    }
     /**
      * @Description InitializingBean 提供的加载bean的初始化方法
      * @Author YingHao He
      * @Since 2019/12/24
      */
    @Override
    public void afterPropertiesSet() throws Exception {
        //做缓存操作
        seckillService.cacheAllCourse();
    }
}
