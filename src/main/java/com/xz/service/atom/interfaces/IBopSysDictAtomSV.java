package com.xz.service.atom.interfaces;


import com.xz.entity.BopSysDict;
import com.xz.entity.BopSysDictExample;

import java.util.List;

public interface IBopSysDictAtomSV {

    int insert(BopSysDict record);

    int updateByPrimaryKey(BopSysDict record);

    int updateByPrimaryKeySelective(BopSysDict record);

    List<BopSysDict> selectByExample(BopSysDictExample example);

    BopSysDict selectByPrimaryKey(String id);

}
