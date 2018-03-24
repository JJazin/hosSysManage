package com.ushier.hospital.illness.web.service.impl;

import com.ushier.hospital.illness.web.entity.CollectEntity;
import com.ushier.hospital.illness.web.mapper.CollectMapper;
import com.ushier.hospital.illness.web.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollectServiceImpl implements CollectService{

    @Autowired
    private CollectMapper mapper;

    @Override
    @Transactional
    public int insert(CollectEntity entity) {
        mapper.deleteByUserAndHos(entity.getUid(), entity.getHosId());
        return mapper.insert(entity);
    }

    @Override
    public int delete(Integer id) {
        return mapper.delete(id);
    }

    @Override
    public int deleteByUserAndHos(Integer uid, Integer hosId) {
        return mapper.deleteByUserAndHos(uid, hosId);
    }

    @Override
    public CollectEntity queryByUserAndHos(Integer uid, Integer hosId) {
        return mapper.queryByUserAndHos(uid, hosId);
    }
}
