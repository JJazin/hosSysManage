package com.green.hospital.illness.web.mapper;

import com.green.hospital.illness.web.entity.UserEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    int insert(UserEntity userEntity);

    int update(UserEntity userEntity);

    @Delete("DELETE FROM _user WHERE id = #{id}")
    int delById(Integer id);

    @Select("SELECT * FROM _user WHERE id = #{id}")
    UserEntity queryById(Integer id);

    @Select("SELECT id, password, role_id, hos_id FROM _user WHERE name = #{name} LIMIT 1")
    UserEntity queryByName(String name);

    List<UserEntity> pageQuery(@Param(value = "position") Integer position,
                               @Param(value = "length") Integer length,
                               @Param(value = "roleId") Integer roleId,
                               @Param(value = "hosId") Integer hosId,
                               @Param(value = "filterValue") String filterValue);

    int queryCount(@Param(value = "roleId") Integer roleId,
                   @Param(value = "hosId") Integer hosId,
                   @Param(value = "filterValue") String filterValue);
}
