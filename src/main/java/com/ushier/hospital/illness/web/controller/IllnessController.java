package com.ushier.hospital.illness.web.controller;

import com.ushier.hospital.illness.web.bean.LayUITableBean;
import com.ushier.hospital.illness.web.bean.ResponseMessageBean;
import com.ushier.hospital.illness.web.entity.UserEntity;
import com.ushier.hospital.illness.web.global.ServerCode;
import com.ushier.hospital.illness.web.global.SessionKey;
import com.ushier.hospital.illness.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/illness")
public class IllnessController {

    private Logger logger = LoggerFactory.getLogger(IllnessController.class);

    @Autowired
    private UserService userService;

    private static final Integer ROLE_ILLNESS = 3;

    @RequestMapping(value = "/pageQuery", method = RequestMethod.GET)
    public LayUITableBean<UserEntity> pageQuery(Integer hosId,
                                                Integer currentPage, Integer limit) {

        logger.info("hospital id = " + hosId + " , currentPage = " + currentPage + " , limit = " + limit);

        int position = (currentPage - 1) * limit;

        return this.userService.pageQuery(position, limit, ROLE_ILLNESS, hosId, null);
    }

    @RequestMapping(value = "/pageQueryForDoctor", method = RequestMethod.GET)
    public LayUITableBean<UserEntity> pageQueryForDoctor(Integer currentPage, Integer limit, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Integer hosId = (Integer) session.getAttribute(SessionKey.HOS_ID);

        int position = (currentPage - 1) * limit;

        return this.userService.pageQuery(position, limit, ROLE_ILLNESS, hosId, null);
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
