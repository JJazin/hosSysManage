package com.green.hospital.illness.web.controller;

import com.green.hospital.illness.web.entity.ResultEntity;
import com.green.hospital.illness.web.service.ResultService;
import com.green.hospital.illness.web.bean.ResponseMessageBean;
import com.green.hospital.illness.web.global.ServerCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/result")
public class ResultController {

    @Autowired
    private ResultService service;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessageBean<?> save(ResultEntity entity) {
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);
        bean.setData(service.insert(entity) > 0 ? true : false);
        return bean;
    }
}
