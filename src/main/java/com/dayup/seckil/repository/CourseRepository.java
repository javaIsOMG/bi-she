package com.dayup.seckil.repository;

import com.dayup.seckil.entity.po.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Classname CourseRepository
 * @Description TODO
 * @Date 2019/12/24 9:40
 * @Created by Yinghao.He
 */
@Repository
public interface CourseRepository extends JpaRepository<Course,String> {

    Course findByCourseNo(String num);
    @Modifying
    @Query("update Course c set stockQuantity=stockQuantity-1 where courseNo=:a and stockQuantity>0")
    int reduceStock(@Param("a") String a);
    @Modifying
    @Query("update Course c set c.coursePic=:pic where c.courseNo=:a")
    int uploadPicByUserName(@Param("pic") String s,@Param("a") String courseNo);
}
