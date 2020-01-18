package com.dayup.seckil.commom;

import com.dayup.seckil.entity.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Classname BaseRedis
 * @Description TODO
 * @Date 2019/12/21 10:15
 * @Created by Yinghao.He
 */
public abstract class BaseRedis<T> {
    @Autowired
    @Qualifier(value = "Redis")
    protected RedisTemplate<Object,Object> redisTemplate;
    protected HashOperations hashOperations;
    protected ValueOperations valueOperations;
    protected abstract String getRedisKey();
    public void put(String key,T domin,long expire){
        hashOperations.put(getRedisKey(),key,domin);
        if (expire!=-1){
            redisTemplate.expire(getRedisKey(),expire, TimeUnit.SECONDS);
        }
    }
    public void setString(String key,T domin,long expire){
        valueOperations.set(key,domin);
        if (expire!=-1){
            redisTemplate.expire(getRedisKey(),expire, TimeUnit.SECONDS);
        }
    }
    @PostConstruct
    private void init(){
        hashOperations=redisTemplate.opsForHash();
        valueOperations=redisTemplate.opsForValue();
    }
    public T get(String key){
       return (T)hashOperations.get(getRedisKey(),key);
    }
    public T getString(String key){
        return (T)valueOperations.get(key);
    }

    public double dec(String courseNo, double i,long expire){
        redisTemplate.expire(courseNo,expire, TimeUnit.SECONDS);
        return valueOperations.increment(courseNo,i);
    }
    public double dec(String courseNo, double i){
        return valueOperations.increment(courseNo,i);
    }
    public Boolean delete(String key){
        Boolean delete = redisTemplate.delete(key);
        return delete;
    }
}
