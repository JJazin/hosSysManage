package com.green.hospital.illness.web.global;

public interface ServerCode {
    String RETURN_OK = "OK";
    String RETURN_FAIL = "FAIL";
    String RETURN_ERROR = "ERROR";

    String MSG_LOGIN_SUCCESS = "登录成功";
    String MSG_REG_SUCCESS = "注册成功";
    String MSG_LOGIN_OUT_SUCCESS = "注销成功";
    String MSG_NO_SUCH_USER = "无此用户";
    String MSG_USERNAME_OR_PASSWORD_ERROR = "用户名 / 密码错误";
    String MSG_SERVER_ERROR = "服务器错误";
    String MSG_NULL_VALUE = "存在空值";
    String MSG_UPDATE_SUCCESS = "更新数据成功";
    String MSG_SAVE_SUCCESS = "保存数据成功";
    String MSG_DELETE_SUCCESS = "删除数据成功";
    String MSG_SAVE_FAIL = "保存数据失败";
    String MSG_UPDATE_FAIL = "更新数据失败";
    String MSG_DELETE_FAIL = "删除数据失败";
    String MSG_PHONE_REPEAT = "手机号码已存在";
    String MSG_USER_REPEAT = "用户已存在";
    String MSG_REG_ERR = "注册失败";

    String MSG_RID_NOT_FOUND = "未找到role_id";

    Integer LAYUI_RETURN_OK = 0;
    Integer LAYUI_RETURN_ERROR = -1;

}
