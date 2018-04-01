package com.green.hospital.illness.web.mapper;

import com.green.hospital.illness.web.entity.MenuEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<MenuEntity> queryByIds(@Param(value = "ids") Integer[] ids);

    List<MenuEntity> queryByRole(@Param(value = "roleId") Integer roleId);


    List<MenuEntity> pageQuery(@Param(value = "position") Integer position,
                               @Param(value = "length") Integer length);

    int queryCount();

    @Select("SELECT id, title FROM _menu WHERE status = 0 AND pid = #{pid}")
    List<MenuEntity> queryMenusByPid(Integer pid);

    int insert(MenuEntity menuEntity);

    int update(MenuEntity menuEntity);

    @Delete("DELETE FROM _menu WHERE id = #{id}")
    int delById(Integer id);

    @Select("SELECT * FROM _menu WHERE status = 0")
    List<MenuEntity> queryAllMenus();

}
