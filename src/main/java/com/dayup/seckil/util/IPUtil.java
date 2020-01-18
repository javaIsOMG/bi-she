package com.dayup.seckil.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * @Classname IPUtil
 * @Description TODO
 * @Date 2019/12/26 10:38
 * @Created by Yinghao.He
 */

public class IPUtil {
    //ip限流工具类
    public static String getIpAddr(HttpServletRequest request){
        String ipAddress=null;
        try{
            ipAddress=request.getHeader("x-forwarded-for");
            if (ipAddress==null||ipAddress.length()==0||"unknown".equalsIgnoreCase(ipAddress)){
                ipAddress=request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress==null||ipAddress.length()==0||"unknown".equalsIgnoreCase(ipAddress)){
                ipAddress=request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress==null||ipAddress.length()==0||"unknown".equalsIgnoreCase(ipAddress)){
                ipAddress=request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")){
                    InetAddress inet=null;
                    try {
                        inet = InetAddress.getLocalHost();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    inet.getHostAddress();
                }
            }
            if (ipAddress!=null&&ipAddress.length()>15){
                if (ipAddress.indexOf(",")>0){
                    ipAddress=ipAddress.substring(0,ipAddress.indexOf(","));
                }
            }
        }catch (Exception e){
            ipAddress="";
        }
        return ipAddress;
    }
}
