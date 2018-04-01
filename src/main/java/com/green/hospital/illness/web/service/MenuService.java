package com.green.hospital.illness.web.service;


import com.green.hospital.illness.web.bean.MenuNodeBean;
import com.green.hospital.illness.web.bean.LayUITableBean;
import com.green.hospital.illness.web.entity.MenuEntity;

import java.util.List;

public interface MenuService {

    List<MenuEntity> queryMenusByRole(Integer roleId);

    LayUITableBean<MenuEntity> pageQuery(Integer position, Integer length);

    List<MenuEntity> queryMenusByPid(Integer pid);

    int insert(MenuEntity menuEntity);

    int update(MenuEntity menuEntity);

    int delById(Integer id);

    List<MenuEntity> queryAllMenus();

    /**
     * 查询角色权限树
     * @param roleId 角色ID
     * @return
     */
    List<MenuNodeBean> queryRolePermissionTree(Integer roleId);

}
