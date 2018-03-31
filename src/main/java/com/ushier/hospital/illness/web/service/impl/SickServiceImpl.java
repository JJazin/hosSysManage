package com.ushier.hospital.illness.web.service.impl;

import com.ushier.hospital.illness.web.bean.LayUITableBean;
import com.ushier.hospital.illness.web.entity.SickEntity;
import com.ushier.hospital.illness.web.global.ServerCode;
import com.ushier.hospital.illness.web.mapper.SickMapper;
import com.ushier.hospital.illness.web.service.SickService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 */
@Service
public class SickServiceImpl implements SickService {
    private Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Autowired
    private SickMapper mapper;


    @Override
    public LayUITableBean<SickEntity> pageQuery(Integer position, Integer length) {

        LayUITableBean<SickEntity> bean = new LayUITableBean<>();

        try {
            List<SickEntity> list = this.mapper.pageQuery(position, length);
            int count = this.mapper.queryCount();

            bean.setCode(ServerCode.LAYUI_RETURN_OK);
            bean.setCount(count);
            bean.setData(list);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return bean;
    }

    @Override
    public int insert(SickEntity sickEntity) {
        try {
            return this.mapper.insert(sickEntity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public int update(SickEntity sickEntity) {
        try {
            return this.mapper.update(sickEntity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public int delById(Integer id) {
        try {
            return this.mapper.delById(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return 0;
    }
}
