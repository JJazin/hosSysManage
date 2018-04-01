package com.green.hospital.illness.web.service;

import com.green.hospital.illness.web.entity.CollectEntity;

public interface CollectService {
    int insert(CollectEntity entity);

    int delete(Integer id);

    int deleteByUserAndHos(Integer uid, Integer hosId);

    CollectEntity queryByUserAndHos(Integer uid, Integer hosId);
}
