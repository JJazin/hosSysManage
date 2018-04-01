package com.green.hospital.illness.web.service;

public interface RoleMenuService {

    /**
     * 更新角色菜单权限
     * @param roleId
     * @param menuIds
     * @return
     */
    int updateRole(Integer roleId, Integer[] menuIds);

}
