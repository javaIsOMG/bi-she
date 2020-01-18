package com.dayup.seckil.controller;

import com.dayup.seckil.commom.BaseController;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.PasswordVo;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.entity.vo.ResultCode;
import com.dayup.seckil.entity.vo.UserVo;
import com.dayup.seckil.service.UserService;
import com.dayup.seckil.util.Md5Util;
import com.dayup.seckil.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Classname LoginController
 * @Description TODO
 * @Date 2019/12/20 16:03
 * @Created by Yinghao.He
 */
@Api(tags = "登陆")
@Slf4j
@RestController
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;

    @ApiOperation(notes = "登陆", value = "用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "账号", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@ModelAttribute(value = "user") @Valid User user, BindingResult bindingResult,
                        HttpSession session,
                        String code, Model model,
                        HttpServletResponse response) {
        log.info("登陆");
        if (bindingResult.hasErrors()) {
            return Result.failure();
        }
        UserVo dbUser = userService.getUser(user.getUsername());
        if (dbUser != null) {
            if (Md5Util.md5ToBack(user.getPassword(), dbUser.getSalt()).equals(dbUser.getPassword())) {
                String token = UUIDUtil.UUID();
                userService.saveUserToRedis(dbUser, token);
                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                response.addCookie(cookie);
                return Result.success();
            } else {
                return Result.failure(ResultCode.USER_LOGIN_ERROR);
            }
        } else {
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
    }

    //    @RequestMapping(value = "/validateCode",method = RequestMethod.GET)
//    public void ValidateCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        //设置body类型
//        response.setContentType("image/jpeg");
//        //防止图片缓存
//        response.setHeader("Pragma","No-cache");
//        response.setHeader("Cache-Control","no-cache");
//        response.setDateHeader("Expires",0);
//        HttpSession session=request.getSession();
//        ValidateCode validateCode=new ValidateCode(120,34,5,100);
//        session.setAttribute("code",validateCode);
//        validateCode.write(response.getOutputStream());
//    }
    @RequestMapping(value = "setPassword", method = RequestMethod.POST)
    public Result setPassword(@Valid PasswordVo password, User user,HttpServletRequest request) {
        log.info("调用修改密码接口");
        Result result = userService.setPassword(password, user,request);
        return result;
    }

}
