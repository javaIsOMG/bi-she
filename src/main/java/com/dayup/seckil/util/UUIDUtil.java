package com.dayup.seckil.util;

import java.util.UUID;

/**
 * @Classname UUIDUtil
 * @Description TODO
 * @Date 2019/12/21 11:48
 * @Created by Yinghao.He
 */
public class UUIDUtil {
    public static String UUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
