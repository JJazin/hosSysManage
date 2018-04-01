package com.green.hospital.illness.web.controller;

import com.green.hospital.illness.web.bean.LayUITableBean;
import com.green.hospital.illness.web.bean.ResponseMessageBean;
import com.green.hospital.illness.web.entity.NoticeEntity;
import com.green.hospital.illness.web.global.ServerCode;
import com.green.hospital.illness.web.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/notice")
public class NoticeController {

    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService service;


    @RequestMapping(value = "/pageQuery", method = RequestMethod.GET)
    public LayUITableBean<NoticeEntity> pageQuery(Integer currentPage, Integer limit){

        int position = (currentPage - 1) * limit;

        return this.service.pageQuery(position, limit);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> save(NoticeEntity entity){
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        int rows = this.service.insert(entity);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(rows > 0 ? ServerCode.MSG_SAVE_SUCCESS : ServerCode.MSG_SAVE_FAIL);
        return bean;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> update(NoticeEntity entity){
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        int rows = this.service.update(entity);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(rows > 0 ? ServerCode.MSG_UPDATE_SUCCESS : ServerCode.MSG_UPDATE_FAIL);
        return bean;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> delById(Integer id){
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        int rows = this.service.delById(id);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(rows > 0 ? ServerCode.MSG_DELETE_SUCCESS : ServerCode.MSG_DELETE_FAIL);
        return bean;
    }
}
