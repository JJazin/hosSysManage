package com.ushier.hospital.illness.web.service.impl;

import com.ushier.hospital.illness.web.bean.LayUITableBean;
import com.ushier.hospital.illness.web.entity.HospitalEntity;
import com.ushier.hospital.illness.web.global.ServerCode;
import com.ushier.hospital.illness.web.mapper.HospitalMapper;
import com.ushier.hospital.illness.web.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalMapper mapper;

    @Override
    public LayUITableBean<HospitalEntity> pageQuery(Integer position, Integer length) {
        LayUITableBean<HospitalEntity> bean = new LayUITableBean<>();

        try {
            List<HospitalEntity> list = this.mapper.pageQuery(position, length);

            int count = this.mapper.queryCount();

            bean.setCode(ServerCode.LAYUI_RETURN_OK);
            bean.setData(list);
            bean.setCount(count);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return bean;
    }

    @Override
    public int insert(HospitalEntity entity) {
        return this.mapper.insert(entity);
    }

    @Override
    public int update(HospitalEntity entity) {
        return this.mapper.update(entity);
    }

    @Override
    public int delById(Integer id) {
        return this.mapper.delById(id);
    }


    @Override
    public HospitalEntity queryById(Integer id) {
        return this.mapper.queryById(id);
    }

    @Override
    public List<HospitalEntity> queryAll() {
        return mapper.queryAll();
    }

    @Override
    public List<HospitalEntity> queryCollectedByUid(Integer uid) {
        return mapper.queryCollectedByUid(uid);
    }
}
