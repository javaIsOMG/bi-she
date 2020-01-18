package com.dayup.seckil.controller;

import com.dayup.seckil.commom.BaseController;
import com.dayup.seckil.entity.po.Student;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.entity.vo.StudentVo;
import com.dayup.seckil.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname StudentController
 * @Description TODO
 * @Date 2020/1/2 14:30
 * @Created by Yinghao.He
 */
@RestController
@Slf4j
public class StudentController extends BaseController {
    @Autowired
    private StudentService studentService;
    @RequestMapping(value = "/getStudentInfo")
    public Result<Student> getStudentInfo(User user){
        Result<Student> result=studentService.getStudentInfo(user);
        return result;
    }
    @RequestMapping(value = "/upload")
    public Result uploadFile(@RequestParam("file")MultipartFile multipartFile, User user) throws IOException {
        log.info("开始调用上传文件接口");
        Result result= studentService.uploadFile(multipartFile,user);
        return result;
    }
    @RequestMapping(value = "setInfo",method =  RequestMethod.POST)
    public Result setInfo(@Valid Student student,User user,@RequestParam("time")String time) throws ParseException {
        log.info("调用用户修改接口");
        Result result=studentService.setInfo(student,user,time);
        return result;
    }
    @RequestMapping(value = "getStudent/list",method = RequestMethod.GET)
    public Result getStudentList(@RequestParam(name = "page",defaultValue = "1")int page,@RequestParam(name = "size",defaultValue = "10") int size){
        Result result=studentService.getStudentList(page, size);
        return result;
    }
    @RequestMapping(value = "student/delete/{studentNum}",method = RequestMethod.GET)
    public Result deleteByStudentNum(@PathVariable("studentNum") String studentNum){
        Result result=studentService.deleteByStudentNum(studentNum);
        return result;
    }
    @RequestMapping(value = "get/info/{studentNum}",method = RequestMethod.GET)
    public Result getInfoByStudentNum(@PathVariable("studentNum") String studentNum){
        log.info("开始调用查询接口");
        Result result=studentService.getStudentInfoByStudentNum(studentNum);
        return result;
    }
    @RequestMapping(value = "add/info",method = RequestMethod.POST)
    public Result addInfo(@Valid StudentVo studentVo) throws ParseException {
        log.info("开始调用新增接口");
        Result result=studentService.addInfo(studentVo);
        return result;
    }
    @RequestMapping(value = "delete/list",method = RequestMethod.POST)
    public Result deleteList(@RequestParam("nums")String[] nums){
        log.info("开始调用批量删除接口");
        String[] result=new String[nums.length-2];
        System.arraycopy(nums,1,result,0,nums.length-2);
        List<String> numList=new ArrayList();
        for(String num:result){
            String substring = num.substring(1, num.length() - 1);
            numList.add(substring);
        }
        Result resultList=studentService.deleteByStudentNumList(numList);
        return resultList;
    }
    @RequestMapping(value = "getStudent/List",method = RequestMethod.GET)
    public Result getStudentListA(@RequestParam(name = "page",defaultValue = "1")int page,@RequestParam(name = "size",defaultValue = "1") int size,
                                  @RequestParam(name = "name")String name){
        Result result=studentService.getStudentList(page, size,name);
        return result;
    }

}
