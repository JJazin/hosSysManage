package com.ushier.hospital.illness.web.mapper;

import com.ushier.hospital.illness.web.entity.DoctorEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DoctorMapper {
    List<DoctorEntity> pageQuery(@Param(value = "hosId") Integer hosId,
                                     @Param(value = "position") Integer position,
                                     @Param(value = "length") Integer length);

    int queryCount(@Param(value = "hosId") Integer hosId);

    int insert(DoctorEntity entity);

    int update(DoctorEntity entity);

    @Delete("DELETE FROM _doctor WHERE id = #{id}")
    int delById(Integer id);

    @Select("SELECT * FROM _doctor WHERE `name`= #{name}")
    DoctorEntity queryByPhone(@Param(value = "phone") String phone);

    @Select("SELECT * FROM _doctor WHERE id = #{id}")
    DoctorEntity queryById(@Param(value = "id") Integer id);

    @Select("SELECT * FROM _doctor WHERE hos_id = #{hosId}")
    List<DoctorEntity> queryAll(@Param(value = "hosId") Integer hosId);

    @Select("SELECT a.* FROM _doctor a LEFT JOIN _dep_doctor b ON a.id = b.doctor_id WHERE b.dep_id = #{depId}")
    List<DoctorEntity> queryByDep(@Param(value = "depId") Integer depId);
}
