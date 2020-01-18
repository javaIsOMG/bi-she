package com.dayup.seckil.service;

import com.dayup.seckil.entity.dto.RecordDto;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.Result;

/**
 * @Classname RecordService
 * @Description TODO
 * @Date 2020/1/15 9:49
 * @Created by Yinghao.He
 */
public interface RecordService {
    Result getRecord(User user);

    Result getRecordByNum(String studentNum);

    Result deleteById(Integer id);


    Result uploadById(Integer id, User user, RecordDto recordDto);

    Result addRecord(String studentNum, User user, RecordDto recordDto);
}
