package com.dayup.seckil.service;

import com.dayup.seckil.entity.po.Course;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.CourseVo;
import com.dayup.seckil.entity.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @Classname CourseService
 * @Description TODO
 * @Date 2019/12/24 9:41
 * @Created by Yinghao.He
 */
public interface CourseService {

    List<Course> getCourseList();

    CourseVo getCourse(String num);

    int reduceStock(String courseNo);

    Result addCourse(Course course, String startTime, String endTime, User user) throws ParseException;

    Result addCoursePic(MultipartFile multipartFile, String courseNo) throws IOException;
}
