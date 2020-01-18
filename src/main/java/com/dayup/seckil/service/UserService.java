package com.dayup.seckil.service;

import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.PasswordVo;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.entity.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2019/12/18 16:51
 * @Created by Yinghao.He
 */
public interface UserService {
    public User registered(User user);

    UserVo getUser(String userName);

    void saveUserToRedis(UserVo dbUser, String token);

    Object getUserFromRedisByToken(String s);

    Result setPassword(PasswordVo password, User user, HttpServletRequest request);
}
