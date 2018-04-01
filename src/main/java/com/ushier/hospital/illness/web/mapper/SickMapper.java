package com.ushier.hospital.illness.web.mapper;

import com.ushier.hospital.illness.web.entity.SickEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 */
@Mapper
public interface SickMapper {


    List<SickEntity> pageQuery(@Param(value = "position") Integer position,
                                 @Param(value = "length") Integer length);

    int queryCount();

    int insert(SickEntity sickEntity);

    int update(SickEntity sickEntity);

    @Delete("DELETE FROM _sick WHERE id = #{id}")
    int delById(Integer id);

}
