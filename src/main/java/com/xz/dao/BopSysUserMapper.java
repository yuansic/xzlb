package com.xz.dao;


import com.xz.entity.BopSysUser;
import com.xz.entity.BopSysUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BopSysUserMapper {
    long countByExample(BopSysUserExample example);

    int deleteByExample(BopSysUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(BopSysUser record);

    int insertSelective(BopSysUser record);

    List<BopSysUser> selectByExample(BopSysUserExample example);

    BopSysUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BopSysUser record, @Param("example") BopSysUserExample example);

    int updateByExample(@Param("record") BopSysUser record, @Param("example") BopSysUserExample example);

    int updateByPrimaryKeySelective(BopSysUser record);

    int updateByPrimaryKey(BopSysUser record);
}