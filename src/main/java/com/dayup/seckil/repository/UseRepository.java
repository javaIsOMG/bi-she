package com.dayup.seckil.repository;

import com.dayup.seckil.entity.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Classname UseRepository
 * @Description TODO
 * @Date 2019/12/18 16:50
 * @Created by Yinghao.He
 */
@Repository
public interface UseRepository extends JpaRepository<User,String> {
    User findByUsername(String userName);
    @Modifying
    @Query("update User u set u.password= :b where u.username= :a")
    int updateByUserName(@Param("a") String username, @Param("b") String newPassword);
}
