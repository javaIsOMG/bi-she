package com.dayup.seckil.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.entity.vo.ResultCode;
import com.dayup.seckil.redis.CourseRedis;
import com.dayup.seckil.util.CourseUtil;
import com.dayup.seckil.entity.po.Course;
import com.dayup.seckil.entity.vo.CourseVo;
import com.dayup.seckil.repository.CourseRepository;
import com.dayup.seckil.service.CourseService;
import com.dayup.seckil.util.OSSUtil;
import com.dayup.seckil.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname CourseServiceImpl
 * @Description TODO
 * @Date 2019/12/24 9:41
 * @Created by Yinghao.He
 */
@Slf4j
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    private static final String ALL_COURSE_REDIS = "allCourseRedis";
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseRedis courseRedis;
    @Override
    public List<Course> getCourseList() {
        //从redis里读
        String string = (String)courseRedis.getString(ALL_COURSE_REDIS);
        List<Course> courses = new ArrayList<>();
        courses=JSONObject.parseArray(string, Course.class);
        if (StringUtils.isEmpty(string)){
            courses= courseRepository.findAll();
            String jsonString = JSONObject.toJSONString(courses);
            courseRedis.setString(ALL_COURSE_REDIS,jsonString,-1);
        }
        return courses;
    }

    @Override
    public CourseVo getCourse(String num) {
        Course course=courseRepository.findByCourseNo(num);
        if (course==null){
            return null;
        }
        return CourseUtil.course(course);
    }

    @Override
    public int reduceStock(String courseNo) {
        return courseRepository.reduceStock(courseNo);
    }

    @Override
    public Result addCourse(Course course, String startTime, String endTime, User user) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = formatter.parse(startTime);
        Date endDate=formatter.parse(endTime);
        course.setTeacherName(user.getUsername());
        course.setStartTime(startDate);
        course.setEndTime(endDate);
        Course flush = courseRepository.saveAndFlush(course);
        if (flush==null){
            log.info("保存失败");
            return Result.failure();
        }
        uploadRedisToCourse();
        courseRedis.setString(flush.getCourseNo(),flush.getStockQuantity(),-1);
        return Result.success(flush.getCourseNo());
    }

    @Override
    public Result addCoursePic(MultipartFile multipartFile, String courseNo) throws IOException {
        String s = OSSUtil.saveFile(multipartFile);
        if (s==null){
            return Result.failure(ResultCode.UPLOAD_FAIL);
        }
        int result=courseRepository.uploadPicByUserName(s,courseNo);
        if (result<=0){
            return Result.failure(ResultCode.UPLOAD_FAIL);
        }
        uploadRedisToCourse();
        return Result.success(ResultCode.UPLOAD_SUCCESS);
    }
    private void uploadRedisToCourse(){
        List<Course> courses = courseRepository.findAll();
        String jsonString = JSONObject.toJSONString(courses);
        courseRedis.setString(ALL_COURSE_REDIS,jsonString,-1);
    }
}
