package com.green.hospital.illness.web.service.impl;

import com.green.hospital.illness.web.mapper.RoleMenuMapper;
import com.green.hospital.illness.web.service.RoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    private Logger logger = LoggerFactory.getLogger(RoleMenuServiceImpl.class);

    @Autowired
    private RoleMenuMapper roleMenuMapper;


    @Override
    @Transactional
    public int updateRole(Integer roleId, Integer[] menuIds) {

        try{
            int delRows = this.roleMenuMapper.deleteByRoleId(roleId);
            int saveRows = this.roleMenuMapper.batchInsert(roleId, menuIds);
            return delRows + saveRows;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return 0;
    }
}
