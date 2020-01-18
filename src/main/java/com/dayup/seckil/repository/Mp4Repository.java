package com.dayup.seckil.repository;

import com.dayup.seckil.entity.po.Mp4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname Mp4Repository
 * @Description TODO
 * @Date 2020/1/9 11:24
 * @Created by Yinghao.He
 */
@Repository
public interface Mp4Repository extends JpaRepository<Mp4,String> {
    @Modifying
    @Query("select u from Mp4  u where u.courseNo = :b ORDER BY u.blues ASC")
    List<Mp4> findAllByCourseNo(@Param("b") String courseNo);
}
