package com.dayup.seckil.controller;

import com.dayup.seckil.commom.BaseController;
import com.dayup.seckil.entity.dto.RecordDto;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * @Classname RecordController
 * @Description TODO
 * @Date 2020/1/15 9:44
 * @Created by Yinghao.He
 */
@Transactional
@RestController
@Slf4j
public class RecordController extends BaseController {
    @Autowired
    private RecordService recordService;
    /**
     * @Description  查询当前用户的所以成绩
     * @param  user
     * @Return
     * @Author YingHao He
     * @Since 2020/1/15
     */
    @RequestMapping(value = "get/Record",method = RequestMethod.GET)
    public Result getRecordByUser(User user){
        Result result=recordService.getRecord(user);
        return result;
    }
    @RequestMapping(value = "get/Record/{studentNumber}",method = RequestMethod.GET)
    public Result getRecordByUser(@PathVariable("studentNumber")String studentNum){
        Result result=recordService.getRecordByNum(studentNum);
        return result;
    }
    @RequestMapping(value = "get/Record/delete/{id}",method = RequestMethod.GET)
    public Result deleteRecordById(@PathVariable("id")Integer id){
        Result result=recordService.deleteById(id);
        return result;
    }
    @RequestMapping(value = "get/Record/upload/{id}",method = RequestMethod.POST)
    public Result uploadRecordById(@PathVariable("id")Integer id, User user, @Valid RecordDto recordDto){
        Result result=recordService.uploadById(id,user,recordDto);
        return result;
    }
    @RequestMapping(value = "get/Record/add/{studentNumber}",method = RequestMethod.POST)
    public Result addRecordStudentNum(@PathVariable("studentNumber")String studentNum, User user, @Valid RecordDto recordDto){
        Result result=recordService.addRecord(studentNum,user,recordDto);
        return result;
    }
}
