package com.ushier.hospital.illness.web.mapper;

import com.ushier.hospital.illness.web.entity.HospitalEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HospitalMapper {
    List<HospitalEntity> pageQuery(@Param(value = "position") Integer position,
                                   @Param(value = "length") Integer length);

    int queryCount();

    int insert(HospitalEntity entity);

    int update(HospitalEntity entity);

    @Delete("DELETE FROM _hospital WHERE id = #{id}")
    int delById(Integer id);

    @Select("SELECT * FROM _hospital WHERE `name`= #{name}")
    HospitalEntity queryByPhone(@Param(value = "phone") String phone);

    @Select("SELECT * FROM _hospital WHERE id = #{id}")
    HospitalEntity queryById(@Param(value = "id") Integer id);

    @Select("SELECT * FROM _hospital")
    List<HospitalEntity> queryAll();

    @Select("SELECT * FROM _hospital a RIGHT JOIN _collect b ON a.id = b.hos_id WHERE b.uid = #{uid}")
    List<HospitalEntity> queryCollectedByUid(@Param(value = "uid") Integer uid);

}
