package com.ushier.hospital.illness.web.service;

import com.ushier.hospital.illness.web.bean.LayUITableBean;
import com.ushier.hospital.illness.web.dto.CaseDTO;
import com.ushier.hospital.illness.web.dto.SeasonSicknessDTO;
import com.ushier.hospital.illness.web.entity.CaseEntity;

import java.util.Date;
import java.util.List;

public interface CaseService {

    CaseEntity queryById(Integer id);

    LayUITableBean<CaseDTO> pageQuery(Integer hosId, Integer did, Integer uid, String filterValue, Integer position, Integer length);

    int insert(CaseEntity t);

    int update(CaseEntity t);

    int delById(Integer id);

    LayUITableBean<SeasonSicknessDTO> seasonSicknessList(Date startTime, Date endTime);
}
