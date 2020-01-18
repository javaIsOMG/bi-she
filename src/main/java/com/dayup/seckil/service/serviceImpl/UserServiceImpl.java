package com.dayup.seckil.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.dayup.seckil.config.UserArgumentResolver;
import com.dayup.seckil.entity.vo.PasswordVo;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.entity.vo.ResultCode;
import com.dayup.seckil.util.Md5Util;
import com.dayup.seckil.util.UUIDUtil;
import com.dayup.seckil.redis.UserRedis;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.UserVo;
import com.dayup.seckil.repository.UseRepository;
import com.dayup.seckil.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2019/12/18 16:52
 * @Created by Yinghao.He
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UseRepository useRepository;
    @Autowired
    private UserRedis userRedis;

    @Override
    public User registered(User user) {
        String username = user.getUsername();
        User byUsername = useRepository.findByUsername(username);
        if (byUsername!=null){
            return null;
        }
        String password = user.getPassword();
        String dbSalt = UUID.randomUUID().toString();
        user.setSalt(dbSalt);
        String md5Password = Md5Util.md5ToBack(password, dbSalt);
        user.setPassword(md5Password);
        user.setId(UUIDUtil.UUID().hashCode());
        return useRepository.saveAndFlush(user);
    }

    @Override
    public UserVo getUser(String userName) {
        UserVo userVo=new UserVo();
        User user = userRedis.get(userName);
        if (user==null){
            user = useRepository.findByUsername(userName);
            if (user!=null){
                userRedis.put(userName,user,-1);
            }else {
                return null;
            }
        }
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }

    @Override
    public void saveUserToRedis(UserVo dbUser, String token) {
        User user=new User();
        BeanUtils.copyProperties(dbUser,user);
        userRedis.put(token,user,-1);
    }

    @Override
    public Object getUserFromRedisByToken(String s) {
        return userRedis.get(s);
    }

    @Override
    public Result setPassword(PasswordVo password, User user, HttpServletRequest request) {
        if (!Md5Util.md5ToBack(password.getPassword(),user.getSalt()).equals(user.getPassword())){
            return Result.failure(ResultCode.NO_PASSWORD);
        }
        String newPassword = Md5Util.md5ToBack(password.getNewpassword(), user.getSalt());
        int i=useRepository.updateByUserName(user.getUsername(),newPassword);
        if (i<=0){
            return Result.failure();
        }
        user.setPassword(newPassword);
        String token = UserArgumentResolver.getCookie(request, "token");
        userRedis.put(token,user,-1);
        return Result.success();
    }
}
