package com.dayup.seckil;

import com.dayup.seckil.redis.UserRedis;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SeckilApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRedis userRedis;

    @Test
    void contextLoads() {
        User user=new User("admin","123",1,"123","s");
        userRedis.put(user.getUsername(),user,300);
    }

}
