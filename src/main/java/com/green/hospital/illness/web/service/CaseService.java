package com.green.hospital.illness.web.service;

import com.green.hospital.illness.web.entity.CaseEntity;
import com.green.hospital.illness.web.bean.LayUITableBean;
import com.green.hospital.illness.web.dto.CaseDTO;
import com.green.hospital.illness.web.dto.SeasonSicknessDTO;

import java.util.Date;

public interface CaseService {

    CaseEntity queryById(Integer id);

    LayUITableBean<CaseDTO> pageQuery(Integer hosId, Integer did, Integer uid, String filterValue, Integer position, Integer length);

    int insert(CaseEntity t);

    int update(CaseEntity t);

    int delById(Integer id);

    LayUITableBean<SeasonSicknessDTO> seasonSicknessList(Date startTime, Date endTime);
}
