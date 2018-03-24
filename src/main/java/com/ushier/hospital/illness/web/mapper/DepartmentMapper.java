package com.ushier.hospital.illness.web.mapper;

import com.ushier.hospital.illness.web.entity.DepartmentEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    List<DepartmentEntity> pageQuery(@Param(value = "hosId") Integer hosId,
                                     @Param(value = "position") Integer position,
                                     @Param(value = "length") Integer length);

    int queryCount(@Param(value = "hosId") Integer hosId);

    int insert(DepartmentEntity entity);

    int update(DepartmentEntity entity);

    @Delete("DELETE FROM _department WHERE id = #{id}")
    int delById(Integer id);

    @Select("SELECT * FROM _department WHERE `name`= #{name}")
    DepartmentEntity queryByPhone(@Param(value = "phone") String phone);

    @Select("SELECT * FROM _department WHERE id = #{id}")
    DepartmentEntity queryById(@Param(value = "id") Integer id);
}
