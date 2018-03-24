package com.ushier.hospital.illness.web.service.impl;

import com.ushier.hospital.illness.web.bean.LayUITableBean;
import com.ushier.hospital.illness.web.entity.RoleEntity;
import com.ushier.hospital.illness.web.global.ServerCode;
import com.ushier.hospital.illness.web.mapper.RoleMapper;
import com.ushier.hospital.illness.web.mapper.RoleMenuMapper;
import com.ushier.hospital.illness.web.service.RoleService;
import com.ushier.hospital.illness.web.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public LayUITableBean<RoleEntity> pageQuery(Integer position, Integer length) {

        LayUITableBean<RoleEntity> bean = new LayUITableBean<>();

        try{
            List<RoleEntity> list = this.roleMapper.pageQuery(position, length);
            int count = this.roleMapper.queryCount();
            bean.setCode(ServerCode.LAYUI_RETURN_OK);
            bean.setData(list);
            bean.setCount(count);

        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return bean;
    }

    @Override
    public int insert(RoleEntity roleEntity) {
        try{
            Date date = DateUtil.getCurrentDate();
            roleEntity.setCreateTime(date);
            roleEntity.setUpdateTime(date);
            return this.roleMapper.insert(roleEntity);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public int update(RoleEntity roleEntity) {
        try{
            Date date = DateUtil.getCurrentDate();
            roleEntity.setUpdateTime(date);
            return this.roleMapper.update(roleEntity);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    @Transactional
    public int delById(Integer id) {
        try{
            return this.roleMapper.delById(id) + this.roleMenuMapper.deleteByRoleId(id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<RoleEntity> queryAll() {
        try{
            return this.roleMapper.queryAll();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public int insertWithRoleMenu(RoleEntity roleEntity, Integer[] menuIds) {

        try{
            Date date = DateUtil.getCurrentDate();
            roleEntity.setCreateTime(date);
            roleEntity.setUpdateTime(date);
            int insertRows = this.roleMapper.insert(roleEntity);
            Integer roleId = roleEntity.getId();
            if(null == roleId)
                throw new RuntimeException("插入角色数据后返回主键ID为null");

            int batchInsertRows = this.roleMenuMapper.batchInsert(roleId, menuIds);

            return insertRows + batchInsertRows;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return 0;
    }
}
