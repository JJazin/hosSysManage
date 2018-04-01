package com.green.hospital.illness.web.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMenuMapper {

    @Select("SELECT menu_id FROM _role_menu WHERE role_id = #{roleId}")
    Integer[] queryMenuIdsByRole(Integer roleId);

    int batchInsert(@Param(value = "roleId") Integer roleId, @Param(value = "menuIds") Integer[] menuIds);

    @Delete("DELETE FROM _role_menu WHERE role_id = #{roleId}")
    int deleteByRoleId(Integer roleId);

}
