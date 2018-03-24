package com.ushier.hospital.illness.web.service;

import com.ushier.hospital.illness.web.entity.ResultEntity;

public interface ResultService {
    int insert(ResultEntity entity);

    ResultEntity queryByRegId(Integer regId);
}
