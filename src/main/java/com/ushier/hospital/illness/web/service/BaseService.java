package com.ushier.hospital.illness.web.service;

import com.ushier.hospital.illness.web.bean.LayUITableBean;

import java.util.List;

public interface BaseService<T> {

    LayUITableBean<T> pageQuery(Integer position, Integer length);

    int insert(T t);

    int update(T t);

    int delById(Integer id);

}
