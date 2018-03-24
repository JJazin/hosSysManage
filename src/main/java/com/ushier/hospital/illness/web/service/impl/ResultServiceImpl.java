package com.ushier.hospital.illness.web.service.impl;

import com.ushier.hospital.illness.web.entity.ResultEntity;
import com.ushier.hospital.illness.web.mapper.ResultMapper;
import com.ushier.hospital.illness.web.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultMapper mapper;

    @Override
    public int insert(ResultEntity entity) {
        return mapper.insert(entity);
    }

    @Override
    public ResultEntity queryByRegId(Integer regId) {
        return mapper.queryByRegId(regId);
    }
}
