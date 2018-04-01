package com.green.hospital.illness.web.controller;

import com.green.hospital.illness.web.bean.LayUITableBean;
import com.green.hospital.illness.web.bean.ResponseMessageBean;
import com.green.hospital.illness.web.entity.UserEntity;
import com.green.hospital.illness.web.global.ServerCode;
import com.green.hospital.illness.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {

    private Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private UserService userService;

    private static final Integer ROLE_DOCTOR = 2;

    @RequestMapping(value = "/pageQuery", method = RequestMethod.GET)
    public LayUITableBean<UserEntity> pageQuery(Integer hosId,
                                                Integer currentPage, Integer limit) {

        logger.info("hospital id = " + hosId + " , currentPage = " + currentPage + " , limit = " + limit);

        int position = (currentPage - 1) * limit;

        return this.userService.pageQuery(position, limit, ROLE_DOCTOR, hosId, null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseMessageBean<?> update(@PathVariable(value = "id") Integer id, UserEntity entity) {
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);
        int res = userService.update(entity);
        bean.setData(res > 0 ? true : false);
        bean.setMsg(res > 0 ? ServerCode.MSG_UPDATE_SUCCESS : ServerCode.MSG_UPDATE_FAIL);
        return bean;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseMessageBean<?> query(@PathVariable(value = "id") Integer id) {
        ResponseMessageBean<UserEntity> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);
        UserEntity entity = userService.queryById(id);
        bean.setData(entity);
        return bean;
    }
}