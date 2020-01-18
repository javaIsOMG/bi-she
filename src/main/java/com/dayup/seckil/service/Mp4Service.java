package com.dayup.seckil.service;

import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;

/**
 * @Classname Mp4Service
 * @Description TODO
 * @Date 2020/1/9 11:25
 * @Created by Yinghao.He
 */
public interface Mp4Service {
    Result addMP4(MultipartFile multipartFile,String courseNo) throws IOException;

    Result getMp4(BigInteger courseNo, Integer num);
}
