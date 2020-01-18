package com.dayup.seckil.entity.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Classname ResultCode
 * @Description TODO
 * @Date 2019/12/21 15:05
 * @Created by Yinghao.He
 */
@Getter
public enum ResultCode {
    SUCCESS(200,"成功"),
    FAIL(500,"失败"),
    FAIL_AND_ERROR(500,"参数未接收成功"),
    USER_LOGIN_ERROR(500201,"登陆失败，用户名或密码错误，请重新输入"),
    USERNAME_EXISTED_ERROR(500,"用户已经存在"),
    COURSE_OUT_STOCK(1001,"被他人抢先一步，可惜！"),
    COURSE_HAVE_APPOINTMENT(1002,"已经预约请到预约区查看预约信息"),
    WAIT_LINK(1003,"排队中"),
    NOT_WAIT_LINK(1004,"未排队,违规操作"),
    REQUEST_TOO_MUCH(1005,"请求太频繁，请30秒后再试"),
    NOT_STOCK(1006,"无订单"),
    LOGOUT_SUCCESS(200,"退出成功"),
    NO_LOGIN(9999,"未登陆"),
    NO_PASSWORD(500,"原始密码不正确"),
    REGISTER_FAIL(500,"新增用户失败"),
    UPLOAD_FAIL(500,"上传失败"),
    UPLOAD_SUCCESS(200,"上传成功"),
    RECORD_IS_CZ(500,"该成绩重复,请重新提交"),
    RECORD_SAVE_FAIL(500,"保存成绩失败"),
    FAIL_DELETE(500,"删除失败");



    private Integer code;
    private String message;
    ResultCode(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
