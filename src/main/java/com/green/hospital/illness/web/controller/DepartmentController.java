package com.green.hospital.illness.web.controller;

import com.green.hospital.illness.web.bean.LayUITableBean;
import com.green.hospital.illness.web.bean.ResponseMessageBean;
import com.green.hospital.illness.web.entity.DepartmentEntity;
import com.green.hospital.illness.web.global.ServerCode;
import com.green.hospital.illness.web.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    private Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService service;

    @RequestMapping(value = "/pageQuery/{hosId}", method = RequestMethod.GET)
    public LayUITableBean<DepartmentEntity> pageQuery(@PathVariable(value = "hosId") Integer hosId,
                                                      Integer currentPage, Integer limit) {

        logger.info("hospital id = " + hosId + " , currentPage = " + currentPage + " , limit = " + limit);

        int position = (currentPage - 1) * limit;

        return this.service.pageQuery(hosId, position, limit);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessageBean<?> save(DepartmentEntity entity) {
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);
        int res = service.insert(entity);
        bean.setData(res > 0 ? true : false);
        return bean;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseMessageBean<?> update(@PathVariable(value = "id") Integer id, DepartmentEntity entity) {
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);
        int res = service.update(entity);
        bean.setData(res > 0 ? true : false);
        bean.setMsg(res > 0 ? ServerCode.MSG_UPDATE_SUCCESS : ServerCode.MSG_UPDATE_FAIL);
        return bean;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseMessageBean<?> query(@PathVariable(value = "id") Integer id) {
        ResponseMessageBean<DepartmentEntity> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);
        DepartmentEntity entity = service.queryById(id);
        bean.setData(entity);
        return bean;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseMessageBean<?> delete(@PathVariable(value = "id") Integer id) {
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);
        int res = service.delById(id);
        bean.setData(res > 0 ? true : false);
        bean.setMsg(res > 0 ? ServerCode.MSG_DELETE_SUCCESS : ServerCode.MSG_DELETE_SUCCESS);
        return bean;
    }
}
