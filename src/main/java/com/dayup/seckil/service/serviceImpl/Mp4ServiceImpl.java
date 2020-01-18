package com.dayup.seckil.service.serviceImpl;

import com.dayup.seckil.entity.dto.Mp4BaseDto;
import com.dayup.seckil.entity.dto.Mp4Dto;
import com.dayup.seckil.entity.po.Mp4;
import com.dayup.seckil.entity.po.Orders;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.repository.Mp4Repository;
import com.dayup.seckil.service.Mp4Service;
import com.dayup.seckil.service.SeckillService;
import com.dayup.seckil.util.OSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname Mp4ServiceImpl
 * @Description TODO
 * @Date 2020/1/9 11:26
 * @Created by Yinghao.He
 */
@Slf4j
@Service
@Transactional
public class Mp4ServiceImpl implements Mp4Service {
    @Autowired
    private Mp4Repository mp4Repository;
    @Autowired
    private SeckillService seckillService;

    @Override
    public Result addMP4(MultipartFile multipartFile,String courseNo) throws IOException {
        Mp4 mp4=OSSUtil.saveMP4(multipartFile,courseNo);
        Mp4 save = mp4Repository.saveAndFlush(mp4);
        if (save==null){
            return Result.failure();
        }
        return Result.success(save.getUrl());
    }

    @Override
    public Result getMp4(BigInteger orderId, Integer num) {
        Result result=seckillService.getOrdersByOrderIdAndUserName(orderId);
        if (result.getCode().equals("500")){
            return result;
        }
        Orders data = (Orders) result.getData();
        String courseNo = data.getCourseNo();
        String courseName=data.getCourseName();
        List<Mp4> mp4=mp4Repository.findAllByCourseNo(courseNo);
        if (mp4.isEmpty()){
            return Result.failure();
        }
        Mp4Dto mp4Dto=getMp4Dto(mp4,num,courseName);
        return Result.success(mp4Dto);
    }

    private Mp4Dto getMp4Dto(List<Mp4> mp4,Integer num,String courseName) {
        Mp4Dto mp4Dto = new Mp4Dto();
        ArrayList<Mp4BaseDto> objects = new ArrayList<>();
        for (Mp4 i:mp4){
            Mp4BaseDto mp4BaseDto=new Mp4BaseDto();
            if (i.getBlues()==num){
                mp4Dto.setUrl(i.getUrl());
            }
            mp4BaseDto.setName(i.getMp4Name());
            mp4BaseDto.setNum(i.getBlues());
            objects.add(mp4BaseDto);
        }
        mp4Dto.setData(objects);
        mp4Dto.setCourseName(courseName);
        return  mp4Dto;
    }
}
