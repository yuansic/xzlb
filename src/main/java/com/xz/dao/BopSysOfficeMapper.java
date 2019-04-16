package com.xz.dao;


import com.xz.entity.BopSysOffice;
import com.xz.entity.BopSysOfficeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BopSysOfficeMapper {
    long countByExample(BopSysOfficeExample example);

    int deleteByExample(BopSysOfficeExample example);

    int deleteByPrimaryKey(String id);

    int insert(BopSysOffice record);

    int insertSelective(BopSysOffice record);

    List<BopSysOffice> selectByExample(BopSysOfficeExample example);

    BopSysOffice selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BopSysOffice record, @Param("example") BopSysOfficeExample example);

    int updateByExample(@Param("record") BopSysOffice record, @Param("example") BopSysOfficeExample example);

    int updateByPrimaryKeySelective(BopSysOffice record);

    int updateByPrimaryKey(BopSysOffice record);
}