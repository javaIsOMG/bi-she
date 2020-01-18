package com.dayup.seckil.repository;

import com.dayup.seckil.entity.po.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname RecordRepository
 * @Description TODO
 * @Date 2020/1/14 18:03
 * @Created by Yinghao.He
 */
@Repository
public interface RecordRepository extends JpaRepository<Record,Integer> {
    @Modifying
    @Query("select r  from Record  r where r.studentNum =:a order by r.testTime  DESC ")
    List<Record> finAllByStudentNum(@Param("a") String studentNumber);
    @Modifying
    @Query("update Record s set s.score = CASE WHEN :#{#a.score} IS NULL THEN s.score ELSE :#{#a.score} END ," +
            "s.studyScore = CASE WHEN :#{#a.studyScore} IS NULL THEN s.studyScore ELSE :#{#a.studyScore} END ," +
            "s.actualStudyScore = CASE WHEN :#{#a.actualStudyScore} IS NULL THEN s.actualStudyScore ELSE :#{#a.actualStudyScore} END ," +
            "s.creator =  CASE WHEN :#{#a.creator} IS NULL THEN s.creator ELSE :#{#a.creator} END ," +
            "s.isBk =  CASE WHEN :#{#a.isBk} IS NULL THEN s.isBk ELSE :#{#a.isBk} END, " +
            "s.isCx =  CASE WHEN :#{#a.isCx} IS NULL THEN s.isCx ELSE :#{#a.isCx} END "+
            "where s.id = :#{#a.id}")
    int uploadByRecord(@Param("a") Record record);

    Record findBySemesterYearsAndSemesterTypeAndCourseName(Integer semesterYears, Integer semesterType, String courseName);
}
