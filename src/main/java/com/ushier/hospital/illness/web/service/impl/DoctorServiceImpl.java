package com.ushier.hospital.illness.web.service.impl;

import com.ushier.hospital.illness.web.bean.LayUITableBean;
import com.ushier.hospital.illness.web.entity.DoctorEntity;
import com.ushier.hospital.illness.web.global.ServerCode;
import com.ushier.hospital.illness.web.mapper.DepDoctorMapper;
import com.ushier.hospital.illness.web.mapper.DoctorMapper;
import com.ushier.hospital.illness.web.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorMapper mapper;
    @Autowired
    private DepDoctorMapper depDoctorMapper;

    @Override
    public LayUITableBean<DoctorEntity> pageQuery(Integer hosId, Integer position, Integer length) {
        LayUITableBean<DoctorEntity> bean = new LayUITableBean<>();

        try {
            List<DoctorEntity> list = this.mapper.pageQuery(hosId, position, length);

            int count = this.mapper.queryCount(hosId);

            bean.setCode(ServerCode.LAYUI_RETURN_OK);
            bean.setData(list);
            bean.setCount(count);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return bean;
    }

    @Override
    public int insert(DoctorEntity entity) {
        return this.mapper.insert(entity);
    }

    @Override
    public int update(DoctorEntity entity) {
        return this.mapper.update(entity);
    }

    @Override
    public int delById(Integer id) {
        return this.mapper.delById(id);
    }


    @Override
    public DoctorEntity queryById(Integer id) {
        return this.mapper.queryById(id);
    }

    @Override
    public Map<String, Object> queryAll(Integer hosId, Integer depId) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", mapper.queryAll(hosId));
        map.put("ids", depDoctorMapper.queryDoctorIds(depId));
        return map;
    }

    @Transactional
    @Override
    public boolean saveDepDoctors(Integer depId, Integer[] doctorIds) {

        if(null != doctorIds && doctorIds.length > 0){
            return depDoctorMapper.deleteByDepId(depId) >= 0 &&
                    depDoctorMapper.batchInsert(depId, doctorIds) > 0;
        }

        return depDoctorMapper.deleteByDepId(depId) > 0;
    }

    @Override
    public List<DoctorEntity> queryAll(Integer depId) {
        return mapper.queryAll(depId);
    }

    @Override
    public List<DoctorEntity> queryByDep(Integer depId) {
        return mapper.queryByDep(depId);
    }
}
