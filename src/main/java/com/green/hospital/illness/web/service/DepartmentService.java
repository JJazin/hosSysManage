package com.green.hospital.illness.web.service;

import com.green.hospital.illness.web.bean.LayUITableBean;
import com.green.hospital.illness.web.entity.DepartmentEntity;

public interface DepartmentService {

    LayUITableBean<DepartmentEntity> pageQuery(Integer hosId, Integer position, Integer length);

    int insert(DepartmentEntity t);

    int update(DepartmentEntity t);

    int delById(Integer id);

    DepartmentEntity queryById(Integer id);

}
