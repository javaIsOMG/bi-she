package com.dayup.seckil.config;

import com.dayup.seckil.entity.po.User;
import com.dayup.seckil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Classname UserArgumentResolver   参数解析器
 * @Description TODO
 * @Date 2019/12/21 13:50
 * @Created by Yinghao.He
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserService userService;
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> p_class=methodParameter.getParameterType();
        return p_class== User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request=nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String request_param=request.getParameter("token");
        String cookies_token=getCookie(request,"token");
        if (request_param==null&&cookies_token==null){
            return null;
        }
        return userService.getUserFromRedisByToken((request_param!=null?request_param:cookies_token));
    }

    public static String getCookie(HttpServletRequest request, String token) {
        Cookie[] cookies=request.getCookies();
        for (Cookie cookie:cookies){
            if (cookie.getName().equals(token)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
