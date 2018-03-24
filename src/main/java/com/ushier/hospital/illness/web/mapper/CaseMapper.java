package com.ushier.hospital.illness.web.mapper;

import com.ushier.hospital.illness.web.dto.CaseDTO;
import com.ushier.hospital.illness.web.dto.SeasonSicknessDTO;
import com.ushier.hospital.illness.web.entity.CaseEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface CaseMapper {
    int insert(CaseEntity entity);

    int update(CaseEntity entity);

    @Delete("DELETE FROM _case WHERE id = #{id}")
    int delById(Integer id);

    @Select("SELECT * FROM _case WHERE id = #{id}")
    CaseEntity queryById(Integer id);

    List<CaseDTO> pageQuery(@Param(value = "position") Integer position,
                            @Param(value = "length") Integer length,
                            @Param(value = "hosId") Integer hosId,
                            @Param(value = "did") Integer did,
                            @Param(value = "uid") Integer uid,
                            @Param(value = "filterValue") String filterValue);

    int queryCount(@Param(value = "hosId") Integer hosId,
                   @Param(value = "did") Integer did,
                   @Param(value = "uid") Integer uid,
                   @Param(value = "filterValue") String filterValue);

    /**
     * 季节患病提醒
     * @return
     */
    List<SeasonSicknessDTO> seasonSicknessList(@Param(value = "startTime") Date startTime,
                                               @Param(value = "endTime") Date endTime);
}
