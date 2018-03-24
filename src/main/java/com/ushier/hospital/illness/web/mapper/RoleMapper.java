package com.ushier.hospital.illness.web.mapper;

import com.ushier.hospital.illness.web.entity.RoleEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("SELECT * FROM _role LIMIT ${position}, ${length}")
    List<RoleEntity> pageQuery(@Param(value = "position") Integer position, @Param(value = "length") Integer length);

    @Select("SELECT COUNT(*) FROM _role")
    int queryCount();

    int insert(RoleEntity roleEntity);

    int update(RoleEntity roleEntity);

    @Delete("DELETE FROM _role WHERE id = #{id}")
    int delById(Integer id);

    @Select("SELECT id, name FROM _role")
    List<RoleEntity> queryAll();

}
