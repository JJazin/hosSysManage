package com.green.hospital.illness.web.service;

import com.green.hospital.illness.web.bean.LayUITableBean;
import com.green.hospital.illness.web.entity.SickEntity;

/**
 * Created by Administrator on 2018/3/29.
 */
public interface SickService {
    LayUITableBean<SickEntity> pageQuery(Integer position, Integer length);

    int insert(SickEntity entity);

    int update(SickEntity entity);

    int delById(Integer id);
}
