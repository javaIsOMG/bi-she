package com.dayup.seckil.controller;

import com.dayup.seckil.commom.BaseController;
import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.entity.vo.Result;
import com.dayup.seckil.entity.vo.ResultCode;
import com.dayup.seckil.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @Classname RegisterController
 * @Description TODO
 * @Date 2019/12/19 14:04
 * @Created by Yinghao.He
 */
@Api(tags = "注册")
@Slf4j
@RestController
public class RegisterController extends BaseController {
    @Autowired
    private UserService userService;
    private static Logger logger=  LoggerFactory.getLogger(RegisterController.class);
    @ApiOperation(notes = "注册",value = "用户密码重复密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "账号", dataType = "String"),
            @ApiImplicitParam(name = "password",value = "密码",dataType = "String"),
            @ApiImplicitParam(name = "repassword",value = "重复密码",dataType = "String")
    })
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result<String> Register(@ModelAttribute("user")@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
        logger.info("注册");
        User registered = userService.registered(user);
        if (registered==null){
            return Result.failure(ResultCode.USERNAME_EXISTED_ERROR);
        }
        return Result.success();
    }
}
