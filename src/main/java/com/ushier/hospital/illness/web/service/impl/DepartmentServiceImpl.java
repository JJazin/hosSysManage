package com.ushier.hospital.illness.web.service.impl;

import com.ushier.hospital.illness.web.bean.LayUITableBean;
import com.ushier.hospital.illness.web.entity.DepartmentEntity;
import com.ushier.hospital.illness.web.global.ServerCode;
import com.ushier.hospital.illness.web.mapper.DepartmentMapper;
import com.ushier.hospital.illness.web.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper mapper;

    @Override
    public LayUITableBean<DepartmentEntity> pageQuery(Integer hosId, Integer position, Integer length) {
        LayUITableBean<DepartmentEntity> bean = new LayUITableBean<>();

        try {
            List<DepartmentEntity> list = this.mapper.pageQuery(hosId, position, length);

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
    public int insert(DepartmentEntity entity) {
        return this.mapper.insert(entity);
    }

    @Override
    public int update(DepartmentEntity entity) {
        return this.mapper.update(entity);
    }

    @Override
    public int delById(Integer id) {
        return this.mapper.delById(id);
    }


    @Override
    public DepartmentEntity queryById(Integer id) {
        return this.mapper.queryById(id);
    }
}
