package com.dayup.seckil.config;

import com.alibaba.fastjson.JSONObject;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.entity.vo.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Classname MyInterHandler
 * @Description TODO
 * @Date 2019/12/30 14:45
 * @Created by Yinghao.He
 */
@Component
@Slf4j
public class MyInterHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies==null){
            resultJson(response);
            return false;
        }
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals("token")) {
                log.info("已经登陆，正常运行");
               return true;
            }
        }
        resultJson(response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
    private void resultJson(HttpServletResponse response){
        PrintWriter writer = null;
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        try {
            writer = response.getWriter();
            Result failure = Result.failure(ResultCode.NO_LOGIN);
            String jsonString = JSONObject.toJSONString(failure);
            writer.write(jsonString);
            log.info("返回json成功");
        } catch (Exception e) {
            log.info("换回json失败");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
