package com.green.hospital.illness.web.controller;

import com.green.hospital.illness.web.bean.LayUITableBean;
import com.green.hospital.illness.web.bean.ResponseMessageBean;
import com.green.hospital.illness.web.entity.SickEntity;
import com.green.hospital.illness.web.global.ServerCode;
import com.green.hospital.illness.web.service.SickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/3/29.
 */
@RestController
@RequestMapping(value = "/sickness")
public class SickController {

    @Autowired
    private SickService sickService;

    @RequestMapping(value = "/pageQuery", method = RequestMethod.GET)
    public LayUITableBean<SickEntity> pageQuery(Integer currentPage, Integer limit) {

        int position = (currentPage - 1) * limit;

        return this.sickService.pageQuery(position, limit);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> save(SickEntity entity) {
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);
        int rows = this.sickService.insert(entity);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(rows > 0 ? ServerCode.MSG_SAVE_SUCCESS : ServerCode.MSG_SAVE_FAIL);
        return bean;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> update(SickEntity entity) {
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        int rows = this.sickService.update(entity);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(rows > 0 ? ServerCode.MSG_UPDATE_SUCCESS : ServerCode.MSG_UPDATE_FAIL);
        return bean;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> delete(Integer id) {
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        int rows = this.sickService.delById(id);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(rows > 0 ? ServerCode.MSG_DELETE_SUCCESS : ServerCode.MSG_DELETE_FAIL);
        return bean;
    }
}
