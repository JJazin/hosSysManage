package com.green.hospital.illness.web.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepDoctorMapper {

    @Select("SELECT doctor_id FROM _dep_doctor WHERE dep_id = #{depId}")
    Integer[] queryDoctorIds(@Param(value = "depId") Integer depId);

    int deleteByDepId(@Param(value = "depId") Integer depId);

    int batchInsert(@Param(value = "depId") Integer depId, @Param(value = "doctorIds") Integer[] doctorIds);
}
