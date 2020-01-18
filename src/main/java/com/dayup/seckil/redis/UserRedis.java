package com.dayup.seckil.redis;

import com.dayup.seckil.commom.BaseRedis;
import com.dayup.seckil.entity.po.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Classname UserRedis
 * @Description TODO
 * @Date 2019/12/21 10:20
 * @Created by Yinghao.He
 */
@Repository
public class UserRedis extends BaseRedis<User> {
    private static final String REDIS_KEY="com.dayup.seckil.redis.UserRedis";
    @Override
    protected String getRedisKey() {
        return REDIS_KEY;
    }
    public Boolean delete(){
        String key=REDIS_KEY;
        Boolean delete = redisTemplate.delete(key);
        return delete;
    }
}
