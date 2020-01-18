package com.dayup.seckil.kafka;

import com.dayup.seckil.redis.UserRedis;
import com.dayup.seckil.entity.po.Orders;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.repository.SeckillRepository;
import com.dayup.seckil.service.CourseService;
import com.dayup.seckil.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Classname KafkaConsumer
 * @Description TODO
 * @Date 2019/12/25 15:35
 * @Created by Yinghao.He
 */
@Slf4j
@Component
public class KafkaConsumer {
    @Autowired
    private SeckillService seckillService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SeckillRepository seckillRepository;
    @Autowired
    private UserRedis userRedis;
    @KafkaListener(id = "PlaceTheOrder",topics = "test",groupId = "skill")
    public void kafkaListener(ConsumerRecord<?,?>record)  {

        String s = record.value().toString();
        String[] split = s.split("hello");
        if (StringUtils.isEmpty(split)){
            return;
        }
        try{
            String courseNo=split[0];
            String  userString=split[1];
            User user = userRedis.get(userString);
            Orders orders = seckillService.InventoryReduction(user, courseNo);
            log.info("消费成功");
        }catch (Exception e){
            log.info("消费失败"+s);
        }

    }
}
