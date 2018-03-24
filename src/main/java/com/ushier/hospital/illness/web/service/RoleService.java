package com.ushier.hospital.illness.web.service;

import com.ushier.hospital.illness.web.bean.LayUITableBean;
import com.ushier.hospital.illness.web.entity.RoleEntity;

import java.util.List;

public interface RoleService {

    LayUITableBean<RoleEntity> pageQuery(Integer position, Integer length);

    int insert(RoleEntity roleEntity);

    int update(RoleEntity roleEntity);

    int delById(Integer id);

    List<RoleEntity> queryAll();

    /**
     * 插入角色同时插入分配的权限菜单
     * @param roleEntity
     * @param menuIds
     * @return
     */
    int insertWithRoleMenu(RoleEntity roleEntity, Integer[] menuIds);
}
