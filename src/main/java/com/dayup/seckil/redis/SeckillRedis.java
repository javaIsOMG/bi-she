package com.dayup.seckil.redis;

import com.dayup.seckil.commom.BaseRedis;
import com.dayup.seckil.entity.po.Orders;
import com.dayup.seckil.entity.po.User;
import org.springframework.stereotype.Repository;

/**
 * @Classname SeckillRedis
 * @Description TODO
 * @Date 2019/12/26 9:34
 * @Created by Yinghao.He
 */
@Repository
public class SeckillRedis extends BaseRedis<String> {
    private static final String REDIS_KEY="com.dayup.seckil.redis";
    @Override
    protected String getRedisKey() {
        return REDIS_KEY;
    }
}
