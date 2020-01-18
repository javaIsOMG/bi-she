package com.dayup.seckil.redis;

import com.dayup.seckil.commom.BaseRedis;
import org.springframework.stereotype.Repository;

/**
 * @Classname CourseRedis
 * @Description TODO
 * @Date 2019/12/24 10:10
 * @Created by Yinghao.He
 */
@Repository
public class CourseRedis extends BaseRedis {
    private static final String REDIS_KEY="com.dayup.seckil.redis.CourseRedis";
    @Override
    protected String getRedisKey() {
        return REDIS_KEY;
    }
}
