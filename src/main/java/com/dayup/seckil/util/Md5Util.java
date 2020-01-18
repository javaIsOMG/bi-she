package com.dayup.seckil.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Classname Md5Util
 * @Description TODO
 * @Date 2019/12/19 15:58
 * @Created by Yinghao.He
 */
public class Md5Util {
    private static String salt="jiaYan";
    /**
     * @Description MD5加密
     * @param
     * @Return
     * @Author YingHao He
     * @Since 2019/12/19
     */
    //第一次加盐md5加密
    private static String inputToBack(String str){
        return DigestUtils.md5Hex(str+salt);
    }
    //第二次加盐md5加密
    public static String md5ToBack(String str,String dbSalt){
        String back = inputToBack(str);
        String md5Hex = DigestUtils.md5Hex(back+dbSalt);
        return md5Hex;
    }
}
