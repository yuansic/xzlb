package com.xz.service.atom.interfaces;


import com.xz.entity.BopSysUser;
import com.xz.entity.BopSysUserExample;

import java.util.List;

/**
 * @yuansc
 * @data 2019年2月21日
 */
public interface ISysUserAtomSV {

    List<BopSysUser> selectByExample(BopSysUserExample example);

    BopSysUser selectByPrimaryKey(String id);

    int updateByPrimaryKey(BopSysUser record);

    int updateByPrimaryKeySelective(BopSysUser record);

    int insert(BopSysUser record);

}
