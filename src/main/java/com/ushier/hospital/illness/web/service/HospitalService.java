package com.ushier.hospital.illness.web.service;

import com.ushier.hospital.illness.web.entity.HospitalEntity;

import java.util.List;

public interface HospitalService extends BaseService<HospitalEntity> {

    HospitalEntity queryById(Integer id);

    List<HospitalEntity> queryAll();

    List<HospitalEntity> queryCollectedByUid(Integer uid);
}
