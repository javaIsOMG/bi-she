package com.dayup.seckil.controller;

import com.dayup.seckil.commom.BaseController;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.service.Mp4Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @Classname Mp4Controller
 * @Description TODO
 * @Date 2020/1/9 11:25
 * @Created by Yinghao.He
 */
@RestController
@Slf4j
public class Mp4Controller extends BaseController {
    @Autowired
    private Mp4Service mp4Service;
    @RequestMapping(value = "add/course/mp4/{courseNo}",method = RequestMethod.POST)
    public Result addMp4(@PathVariable("courseNo")String courseNo,
                         @RequestParam("file")MultipartFile multipartFile) throws IOException {
        log.info("添加视频接口");
        Result result=mp4Service.addMP4(multipartFile,courseNo);
        return result;
    }
    @RequestMapping(value = "get/course/mp4/{orderId}",method = RequestMethod.GET)
    public Result getMp4ByOrderId(@PathVariable("orderId") String orderId,
                         @RequestParam(value = "num",defaultValue = "1")Integer num){
        log.info("获取视频接口");
        BigInteger bigInteger = new BigInteger(orderId);
        Result result =mp4Service.getMp4(bigInteger,num);
        return result;
    }

}
