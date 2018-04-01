package com.green.hospital.illness.web.service;

import com.green.hospital.illness.web.bean.LayUITableBean;
import com.green.hospital.illness.web.entity.DoctorEntity;

import java.util.List;
import java.util.Map;

public interface DoctorService {

    LayUITableBean<DoctorEntity> pageQuery(Integer hosId, Integer position, Integer length);

    int insert(DoctorEntity t);

    int update(DoctorEntity t);

    int delById(Integer id);

    DoctorEntity queryById(Integer id);

    /**
     * 查询所有医生以及属于该科室的医生id列表
     * @param hosId
     * @param depId
     * @return
     */
    Map<String, Object> queryAll(Integer hosId, Integer depId);

    boolean saveDepDoctors(Integer depId, Integer[] doctorIds);

    List<DoctorEntity> queryAll(Integer depId);

    List<DoctorEntity> queryByDep(Integer depId);

}
