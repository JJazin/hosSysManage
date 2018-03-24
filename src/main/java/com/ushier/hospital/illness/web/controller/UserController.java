package com.ushier.hospital.illness.web.controller;

import com.ushier.hospital.illness.web.bean.LayUITableBean;
import com.ushier.hospital.illness.web.bean.ResponseMessageBean;
import com.ushier.hospital.illness.web.entity.UserEntity;
import com.ushier.hospital.illness.web.global.ServerCode;
import com.ushier.hospital.illness.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/pageQuery", method = RequestMethod.GET)
    public LayUITableBean<UserEntity> pageQuery(Integer currentPage,
                                                Integer limit,
                                                Integer roleId,
                                                Integer hostId,
                                                @RequestParam(value = "filterValue", required = false) String filterValue){

        int position = (currentPage - 1) * limit;

        return this.userService.pageQuery(position, limit, roleId, hostId, filterValue);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> login(String name, String password, HttpServletRequest request){

        return this.userService.login(name, password, request);
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> loginOut(HttpServletRequest request){
        return this.userService.loginOut(request);
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> reg(UserEntity userEntity, HttpServletRequest request){
        return this.userService.reg(userEntity,request);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> save(UserEntity userEntity){
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        int rows = this.userService.insert(userEntity);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(rows > 0 ? ServerCode.MSG_SAVE_SUCCESS : ServerCode.MSG_SAVE_FAIL);
        return bean;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> update(UserEntity userEntity){
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        int rows = this.userService.update(userEntity);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(rows > 0 ? ServerCode.MSG_UPDATE_SUCCESS : ServerCode.MSG_UPDATE_FAIL);
        return bean;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> delete(Integer id){
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        int rows = this.userService.delById(id);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(rows > 0 ? ServerCode.MSG_DELETE_SUCCESS : ServerCode.MSG_DELETE_FAIL);
        return bean;
    }

}
