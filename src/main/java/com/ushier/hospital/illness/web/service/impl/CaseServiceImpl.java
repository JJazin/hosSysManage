package com.ushier.hospital.illness.web.service.impl;

import com.ushier.hospital.illness.web.bean.LayUITableBean;
import com.ushier.hospital.illness.web.dto.CaseDTO;
import com.ushier.hospital.illness.web.dto.SeasonSicknessDTO;
import com.ushier.hospital.illness.web.entity.CaseEntity;
import com.ushier.hospital.illness.web.entity.DoctorEntity;
import com.ushier.hospital.illness.web.global.ServerCode;
import com.ushier.hospital.illness.web.global.SicknessEnum;
import com.ushier.hospital.illness.web.mapper.CaseMapper;
import com.ushier.hospital.illness.web.mapper.DepDoctorMapper;
import com.ushier.hospital.illness.web.mapper.DoctorMapper;
import com.ushier.hospital.illness.web.service.CaseService;
import com.ushier.hospital.illness.web.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseMapper mapper;

    @Override
    public LayUITableBean<CaseDTO> pageQuery(Integer hosId, Integer did, Integer uid, String filterValue, Integer position, Integer length) {
        LayUITableBean<CaseDTO> bean = new LayUITableBean<>();

        try {
            List<CaseDTO> list = this.mapper.pageQuery(position, length, hosId, did, uid, filterValue);

            if(null != list && list.size() > 0){
                for(CaseDTO dto : list){
                    SicknessEnum se = SicknessEnum.getEnum(dto.getSicknessId());
                    if(null != se){
                        dto.setSicknessName(se.getDesc());
                    }
                }
            }

            int count = this.mapper.queryCount(hosId, did, uid, filterValue);

            bean.setCode(ServerCode.LAYUI_RETURN_OK);
            bean.setData(list);
            bean.setCount(count);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return bean;
    }

    @Override
    public int insert(CaseEntity entity) {

        entity.setCreateTime(new Date());
        return this.mapper.insert(entity);
    }

    @Override
    public int update(CaseEntity entity) {
        return this.mapper.update(entity);
    }

    @Override
    public int delById(Integer id) {
        return this.mapper.delById(id);
    }

    @Override
    public LayUITableBean<SeasonSicknessDTO> seasonSicknessList(Date startTime, Date endTime) {
        LayUITableBean<SeasonSicknessDTO> bean = new LayUITableBean<>();

        List<SeasonSicknessDTO> list = this.mapper.seasonSicknessList(startTime, endTime);
        for(SeasonSicknessDTO dto : list){
            SicknessEnum se = SicknessEnum.getEnum(dto.getSicknessId());
            if(null != se){
                dto.setSicknessName(se.getDesc());
            }
        }

        bean.setCode(ServerCode.LAYUI_RETURN_OK);
        bean.setData(list);
        bean.setCount(0);
        return bean;
    }


    @Override
    public CaseEntity queryById(Integer id) {
        return this.mapper.queryById(id);
    }

}
