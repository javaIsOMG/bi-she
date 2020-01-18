package com.dayup.seckil.util;

import com.dayup.seckil.entity.po.Course;
import com.dayup.seckil.entity.vo.CourseVo;


/**
 * @Classname CourseUtil
 * @Description TODO
 * @Date 2019/12/24 14:07
 * @Created by Yinghao.He
 */
public class CourseUtil {



    public static final int COURSE_NOT_START=0;//未开始
    public static final int COURSE__STARTING=1;//已经开始
    public static final int COURSE__STARTED=2;//已经结束

    public static CourseVo course(Course course){
        CourseVo courseVo=new CourseVo();
        courseVo.setCourse(course);
        long startTime =course.getStartTime().getTime();
        long endTime=course.getEndTime().getTime();
        long now =System.currentTimeMillis();
        int remainTime=0;
        int status=COURSE_NOT_START;
        if (now<startTime){
            remainTime= (int) ((startTime-now)/1000);
        }else if (now>endTime){
            status=COURSE__STARTED;
            remainTime=-1;
        }else {
            status=COURSE__STARTING;
            remainTime=-1;
        }
        courseVo.setRemainTime(remainTime);
        courseVo.setCourseStatus(status);
        return courseVo;
    }
}
