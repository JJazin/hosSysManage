package com.green.hospital.illness.web.service;

import com.green.hospital.illness.web.bean.LayUITableBean;

public interface BaseService<T> {

    LayUITableBean<T> pageQuery(Integer position, Integer length);

    int insert(T t);

    int update(T t);

    int delById(Integer id);

}
