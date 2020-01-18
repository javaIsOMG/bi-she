package com.dayup.seckil.controller;

import com.dayup.seckil.commom.BaseController;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.entity.vo.ResultCode;
import com.dayup.seckil.redis.UserRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname LogoutCoutroller
 * @Description TODO
 * @Date 2019/12/30 10:54
 * @Created by Yinghao.He
 */
@RestController
@Slf4j
public class LogoutController extends BaseController {
    @Autowired
    private UserRedis userRedis;
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public Result logout(HttpServletResponse response, HttpServletRequest request,
                                 User user){
        Cookie cookie=new Cookie("token",null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        Boolean delete = userRedis.delete();
        if (delete!=true){
            return Result.failure();
        }
        log.info("成功退出");
        return Result.success(ResultCode.LOGOUT_SUCCESS);
    }
}
