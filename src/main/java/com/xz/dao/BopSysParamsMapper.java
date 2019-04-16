package com.xz.dao;


import com.xz.entity.BopSysParams;
import com.xz.entity.BopSysParamsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BopSysParamsMapper {
    long countByExample(BopSysParamsExample example);

    int deleteByExample(BopSysParamsExample example);

    int deleteByPrimaryKey(String id);

    int insert(BopSysParams record);

    int insertSelective(BopSysParams record);

    List<BopSysParams> selectByExample(BopSysParamsExample example);

    BopSysParams selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BopSysParams record, @Param("example") BopSysParamsExample example);

    int updateByExample(@Param("record") BopSysParams record, @Param("example") BopSysParamsExample example);

    int updateByPrimaryKeySelective(BopSysParams record);

    int updateByPrimaryKey(BopSysParams record);
}