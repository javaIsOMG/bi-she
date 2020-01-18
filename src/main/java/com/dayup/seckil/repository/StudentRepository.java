package com.dayup.seckil.repository;

import com.dayup.seckil.entity.po.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Classname StudentRepository
 * @Description TODO
 * @Date 2020/1/2 15:08
 * @Created by Yinghao.He
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> , JpaSpecificationExecutor<Student> {

    Student findByUserName(String username);

    @Modifying
    @Query("update Student  u set u.picUrl = :b where u.userName = :a")
    int setByUserName(@Param("a") String userName, @Param("b") String picUrl);
    @Modifying
    @Query("update Student s set s.studentName = CASE WHEN :#{#a.studentName} IS NULL THEN s.studentName ELSE :#{#a.studentName} END ," +
            "s.birthTime = CASE WHEN :#{#a.birthTime} IS NULL THEN s.birthTime ELSE :#{#a.birthTime} END ," +
            "s.age = CASE WHEN :#{#a.age} IS NULL THEN s.age ELSE :#{#a.age} END ," +
            "s.declaration =  CASE WHEN :#{#a.declaration} IS NULL THEN s.declaration ELSE :#{#a.declaration} END ," +
            "s.studentNumber =  CASE WHEN :#{#a.studentNumber} IS NULL THEN s.studentNumber ELSE :#{#a.studentNumber} END, " +
            "s.hobby =  CASE WHEN :#{#a.hobby} IS NULL THEN s.hobby ELSE :#{#a.hobby} END, " +
            "s.studentProfessional =  CASE WHEN :#{#a.studentProfessional} IS NULL THEN s.studentProfessional ELSE :#{#a.studentProfessional} END ," +
            "s.sex =  CASE WHEN :#{#a.sex} IS NULL THEN s.sex ELSE :#{#a.sex} END, " +
            "s.phone =  CASE WHEN :#{#a.phone} IS NULL THEN s.phone ELSE :#{#a.phone} END " +
            "where s.userName = :userName")
    int setInfoByUserName(@Param("a") Student student,@Param("userName") String userName);


    int deleteByStudentNumber(String studentNum);

    Student findByStudentNumber(String studentNum);
    @Modifying
    @Query("delete from Student u where u.studentNumber in (:numList)")
    int deleteListByStudentNumber(@Param("numList") List<String> numList);
}
