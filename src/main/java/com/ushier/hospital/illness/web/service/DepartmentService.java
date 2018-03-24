package com.ushier.hospital.illness.web.service;

import com.ushier.hospital.illness.web.bean.LayUITableBean;
import com.ushier.hospital.illness.web.entity.DepartmentEntity;

import java.util.List;

public interface DepartmentService {

    LayUITableBean<DepartmentEntity> pageQuery(Integer hosId, Integer position, Integer length);

    int insert(DepartmentEntity t);

    int update(DepartmentEntity t);

    int delById(Integer id);

    DepartmentEntity queryById(Integer id);

}
