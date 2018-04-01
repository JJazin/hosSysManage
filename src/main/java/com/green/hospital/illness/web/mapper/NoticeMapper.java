package com.green.hospital.illness.web.mapper;

import com.green.hospital.illness.web.entity.NoticeEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {


    List<NoticeEntity> pageQuery(@Param(value = "position") Integer position,
                               @Param(value = "length") Integer length);

    int queryCount();

    int insert(NoticeEntity NoticeEntity);

    int update(NoticeEntity NoticeEntity);

    @Delete("DELETE FROM _notice WHERE id = #{id}")
    int delById(Integer id);

}
