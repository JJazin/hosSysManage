package com.ushier.hospital.illness.web.mapper;

import com.ushier.hospital.illness.web.entity.ResultEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ResultMapper {

    int insert(ResultEntity entity);

    @Select("SELECT * FROM _result WHERE reg_id = #{regId}")
    ResultEntity queryByRegId(@Param(value = "regId") Integer regId);
}
