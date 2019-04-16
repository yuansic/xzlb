package com.xz.dao;


import com.xz.entity.BopSysDict;
import com.xz.entity.BopSysDictExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BopSysDictMapper {
    long countByExample(BopSysDictExample example);

    int deleteByExample(BopSysDictExample example);

    int deleteByPrimaryKey(String id);

    int insert(BopSysDict record);

    int insertSelective(BopSysDict record);

    List<BopSysDict> selectByExample(BopSysDictExample example);

    BopSysDict selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BopSysDict record, @Param("example") BopSysDictExample example);

    int updateByExample(@Param("record") BopSysDict record, @Param("example") BopSysDictExample example);

    int updateByPrimaryKeySelective(BopSysDict record);

    int updateByPrimaryKey(BopSysDict record);
}