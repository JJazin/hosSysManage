package com.green.hospital.illness.web.service;

import com.green.hospital.illness.web.entity.ResultEntity;

public interface ResultService {
    int insert(ResultEntity entity);

    ResultEntity queryByRegId(Integer regId);
}
