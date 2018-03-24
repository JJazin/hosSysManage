package com.ushier.hospital.illness.web.service.impl;

import com.ushier.hospital.illness.web.bean.LayUITableBean;
import com.ushier.hospital.illness.web.entity.NoticeEntity;
import com.ushier.hospital.illness.web.global.ServerCode;
import com.ushier.hospital.illness.web.mapper.NoticeMapper;
import com.ushier.hospital.illness.web.service.NoticeService;
import com.ushier.hospital.illness.web.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Autowired
    private NoticeMapper mapper;


    @Override
    public LayUITableBean<NoticeEntity> pageQuery(Integer position, Integer length) {

        LayUITableBean<NoticeEntity> bean = new LayUITableBean<>();

        try{
            List<NoticeEntity> list = this.mapper.pageQuery(position, length);
            int count = this.mapper.queryCount();

            bean.setCode(ServerCode.LAYUI_RETURN_OK);
            bean.setCount(count);
            bean.setData(list);

        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return bean;
    }

    @Override
    public int insert(NoticeEntity menuEntity) {
        try{
            Date date = DateUtil.getCurrentDate();
            menuEntity.setCreateTime(date);

            return this.mapper.insert(menuEntity);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public int update(NoticeEntity menuEntity) {
        try{
            return this.mapper.update(menuEntity);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public int delById(Integer id) {
        try{
            return this.mapper.delById(id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return 0;
    }
}
