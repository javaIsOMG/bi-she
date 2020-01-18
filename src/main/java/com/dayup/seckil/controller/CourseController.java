package com.dayup.seckil.controller;

import com.dayup.seckil.commom.BaseController;
import com.dayup.seckil.entity.po.Course;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.CourseVo;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @Classname CourseController
 * @Description TODO
 * @Date 2019/12/24 9:43
 * @Created by Yinghao.He
 */
@Api(tags = "课程管理")
@Slf4j
@RestController
public class CourseController extends BaseController {
    @Autowired
    private CourseService courseService;
    @ApiOperation(notes = "获取所有课程列表",value = "无参数")
    @RequestMapping(value = "/courseList",method = RequestMethod.GET)
    public Result<List<Course>> getCourseList(){
        log.info("获得所有课程");
        List<Course> courses=courseService.getCourseList();
        return Result.success(courses);
    }
    @ApiOperation(notes = "获取课程详情",value = "无参数")
    @ApiImplicitParam(name = "courseNo", value = "课程编号", dataType = "String")
    @RequestMapping(value = "/course/{courseNo}",method = RequestMethod.GET)
    public Result<CourseVo> getCourse(@PathVariable("courseNo")String courseNo){
        log.info("获取课程详情");
        CourseVo courseVo=courseService.getCourse(courseNo);
        if (courseVo==null){
            return Result.failure();
        }
        return Result.success(courseVo);
    }
    @RequestMapping(value = "/add/course",method = RequestMethod.POST)
    public Result addCourse(@Valid Course course, @RequestParam("time1")String startTime,
                            @RequestParam("time2")String endTime, User user) throws ParseException {
        log.info("新增课程接口");
        Result result=courseService.addCourse(course,startTime,endTime,user);
        return result;
    }
    @RequestMapping(value = "/add/course/pic/{courseNo}",method = RequestMethod.POST)
    public Result addPic(@RequestParam("file") MultipartFile multipartFile,
                         @PathVariable("courseNo")String courseNo) throws IOException {
        log.info("新增课程图片接口");
        Result result=courseService.addCoursePic(multipartFile,courseNo);
        return result;
    }
}
