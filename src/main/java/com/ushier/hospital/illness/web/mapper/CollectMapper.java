package com.ushier.hospital.illness.web.mapper;

import com.ushier.hospital.illness.web.entity.CollectEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CollectMapper {

    @Insert("INSERT INTO _collect (uid, hos_id) VALUES (#{uid}, #{hosId})")
    int insert(CollectEntity entity);

    @Delete("DELETE FROM _collect WHERE id = #{id}")
    int delete(@Param(value = "id") Integer id);

    @Delete("DELETE FROM _collect WHERE uid = #{uid} AND hos_id = #{hosId}")
    int deleteByUserAndHos(@Param(value = "uid") Integer uid,
                           @Param(value = "hosId") Integer hosId);

    @Select("SELECT * FROM _collect WHERE uid = #{uid} AND hos_id = #{hosId}")
    CollectEntity queryByUserAndHos(@Param(value = "uid") Integer uid,
                                    @Param(value = "hosId") Integer hosId);

}
