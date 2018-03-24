package com.ushier.hospital.illness.web.mapper;

import com.ushier.hospital.illness.web.entity.RegistrationEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface RegistrationMapper {

    int insert(RegistrationEntity entity);

    @Delete("DELETE FROM _registration WHERE id = #{id}")
    int delById(Integer id);

    com.ushier.hospital.illness.web.dto.RegistrationDTO queryByCode(@Param(value = "code") String code);

    @Select("SELECT * FROM _registration WHERE id = #{id}")
    RegistrationEntity queryById(@Param(value = "id") Integer id);

    @Select("SELECT * FROM _registration WHERE hos_id = #{hosId} AND dep_id = #{depId} AND doctor_id = #{doctorId} AND order_date = #{orderDate} ORDER BY create_time DESC")
    List<RegistrationEntity> query(@Param(value = "hosId") Integer hosId,
                                   @Param(value = "depId") Integer depId,
                                   @Param(value = "doctorId") Integer doctorId,
                                   @Param(value = "orderDate") Date orderDate);

}
