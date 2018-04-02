package com.green.hospital.illness.web.service.impl;

import com.green.hospital.illness.web.bean.LayUITableBean;
import com.green.hospital.illness.web.dto.CaseDTO;
import com.green.hospital.illness.web.dto.SeasonSicknessDTO;
import com.green.hospital.illness.web.entity.CaseEntity;
import com.green.hospital.illness.web.entity.SickEntity;
import com.green.hospital.illness.web.global.ServerCode;
import com.green.hospital.illness.web.mapper.CaseMapper;
import com.green.hospital.illness.web.mapper.SickMapper;
import com.green.hospital.illness.web.service.CaseService;
import com.green.hospital.illness.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseMapper mapper;
    @Autowired
    private SickMapper sickMapper;


    @Override
    public LayUITableBean<CaseDTO> pageQuery(Integer hosId, Integer did, Integer uid, String filterValue, Integer position, Integer length) {
        LayUITableBean<CaseDTO> bean = new LayUITableBean<>();

        try {
//            if (did!=null&&did!=0){
//                hosId = null;
//            }
            List<CaseDTO> list = this.mapper.pageQuery(position, length, hosId, did, uid, filterValue);
            Map<Integer, String> sickMap = getSickMap();
            if (null != list && list.size() > 0) {
                for (CaseDTO dto : list) {
                    //获取所有sicknamelist

//                    SicknessEnum se = SicknessEnum.getEnum(dto.getSicknessId());
                    String sickNessName = sickMap.get(dto.getSicknessId());
                    if (null != sickNessName) {
                        dto.setSicknessName(sickNessName);
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

    private Map<Integer, String> getSickMap() {
        List<SickEntity> sickList = sickMapper.getAll();
        Map<Integer, String> sickMap = new HashMap<>();
        for (int i = 0; i < sickList.size(); ++i) {
            sickMap.put(sickList.get(i).getId(), sickList.get(i).getName());
        }
        return sickMap;
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
        Map<Integer, String> sickMap = getSickMap();
        for (SeasonSicknessDTO dto : list) {
            String sickName = sickMap.get(dto.getSicknessId());
            if (null != sickName) {
                dto.setSicknessName(sickName);
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
